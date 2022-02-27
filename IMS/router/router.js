const express=require('express');
const router=express.Router();
const axios=require('axios');
const bodyparser=require('body-parser');
const app=express();
app.use(bodyparser.json());

// const store = require("store2");

const LocalStorage = require('node-localstorage').LocalStorage,
  localStorage = new LocalStorage('./scratch');

//home page
const controller=require('../controller/imsController');
const { json } = require('express/lib/response');
//myprofile 

router.get('/myprofile',(req,res)=>{
  axios.get('http://localhost:8084/ims/getuser/byid',{
    params:{
      id:1
    }
  }).then(function(user){
    console.log(user.data)
    res.render("myProfile",{'user':user.data})
  })
  
});

//login 
router.get('/',(req,res)=>{
  res.render("login")
});
//login 
router.post('/login',(req,res)=>{
  axios({
    method: 'post',
    url: 'http://localhost:8084/ims/authenticate/user',
    params: {
      userId:req.body.userId,
      password:req.body.password
 
    }
  }).then(function(user){
  localStorage.setItem('Name',JSON.stringify(user.data)) 
    console.log(localStorage.getItem('Name'))
    // store('Profile', {name: 'Adam', age: 27, salary: 3452}); 
    res.redirect("/ims/home");
})
});


//homepage
router.get('/home',(req,res)=>{
    res.render("home")
});


//subject
router.get('/addsubjectpage',(req,res)=>{
  res.render("addSubjectPage")
});
router.post('/addsubject',(req,res)=>{
  controller.insertSubject(req,res)
})
router.get('/allsubject',(req,res)=>{
  axios.get('http://localhost:8084/ims/admin/getall/subject')
       
     
    .then(function(subject){
              
              res.render('../views/viewSubject',{'subject' : subject.data})
          })
})

router.get('/deletesubject:id',(req,res)=>
{
  let sid=req.params.id;
    axios.delete('http://localhost:8084/ims/admin/delete/subject',{
        params: {
          id: sid
        }
      }).then(function(){
        res.redirect("/ims/allsubject")
       
    })
})

router.get('/editsubject:id',(req,res)=>{
  
  let sid=req.params.id;
    axios.get('http://localhost:8084/ims/admin/get/subject/byId',{
        params: {
          id: sid
        }
      }).then(function(subject){
        console.log(subject.data)
        res.render("editSubject",{'subject':subject.data})
       
     })
})

router.post('/updatesubject:id',(req,res)=>{
  controller.updateSubject(req,res,req.params.id)
})





//user
router.get('/adduserpage',(req,res)=>{
  res.render("addUsersPage")
});


router.post('/adduser',(req,res)=>{
  controller.insertRecord(req,res)
});


router.get('/alluser:type',(req,res)=>{
  let uType=req.params.type
  axios.get('http://localhost:8084/ims/getallusers/bytype',{
        params:{
            type:uType
        
      }
     
    }).then(function(users){
              console.log(users.data)
              res.render('../views/viewUser',{'users' : users.data})
          })
});



router.get('/edituser:id',(req,res)=>{
  
  let uid=req.params.id;
    axios.get('http://localhost:8084/ims/getuser/byid',{
        params: {
          id: uid
        }
      }).then(function(user){
        console.log(user.data)
        res.render("editUser",{'user':user.data})
       
     })
})


router.post('/updateuser:id',(req,res)=>{
  controller.updateUser(req,res,req.params.id)
})




router.get('/deleteuser:id',(req,res)=>{

  let uid=req.params.id;
    axios.delete('http://localhost:8084/ims/delete/user',{
        params: {
          id: uid
        }
      }).then(function(){
        res.redirect("/ims/home")
       
    })
})



//timetable

router.get('/selectbranch',(req,res)=>{
  res.render("selectbranch")
})
router.get('/addrequest',(req,res)=>{
  res.render('addRequest')
})
  

router.get('/addtimetablepage',(req,res)=>{
 

  axios.all([
    axios.get('http://localhost:8084/ims/getallusers'), 
    axios.get('http://localhost:8084/ims/admin/getall/subject')
  ])
  .then(axios.spread((users, subject) => {
    
    res.render('../views/addTimeTablePage',{'users': users.data, 'subjects':subject.data,'branch':req.query.branch,'year':req.query.year,'sem':req.query.sem})
  })).catch(error => {
    console.log(error)
  });

});


router.post('/addtimetable',(req,res)=>{
  controller.insertTimeTable(req,res)
})

router.get('/viewtimetable',(req,res)=>{
  res.render('viewTimeTableRequest')
})

router.get('/alltimetable',(req,res)=>{
  
 
  axios.get('http://localhost:8084/ims/admin/get/timetable/byweek',{
        params:{
            branch:req.query.branch,
            sem:req.query.sem,
            year:req.query.year
  
      }
     
    }).then(function(time_table){
              
     
              res.render('../views/viewTimeTable',{'time_table' : time_table.data})
          })
  
})













//updating data
router.post('/update/:id', (req,res) => {
  
    controller.update(req,res,req.params.id);
});


//get all employee
router.get('/list',(req,res)=>{
axios.get('http://localhost:8084/employees/getAll').then(function(employees){
        console.log(employees.data)
        res.render('../views/list',{'employees' : employees.data});
    })

});




// //get all Users by type
// router.get('/allusers/:type',(req,res)=>{
//   let uType=req.params.type;
//   axios.get('http://localhost:8084/ims/getallusers/bytype',{
//     params:{
//         type:uType
    
//   }
 
// }).then(function(users){
//           console.log(users.data)
//           res.render('../views/try',{'users' : users.data})
//       })
  
//   });

//edit employee by id
router.get('/edit/:id', (req,res)=>{
    let eid=req.params.id;
    axios.get('http://localhost:8084/employees/get/byId',{
        params: {
          id: eid
        }
      }).then(function(employee){
        res.render("../views/editEmployee", { employee: employee.data});
    })        
});


//view employee by id
router.get('/view/:id', (req,res)=>{
    let eid=req.params.id;
    axios.get('http://localhost:8084/employees/get/byId',{
        params: {
          id: eid
        }
      }).then(function(employee){
        res.render("../views/view", { employee: employee.data});
    })     
});

//delete employee by id
router.get('/delete/:id',(req, res)=> {
    let eid=req.params.id;
    axios.delete('http://localhost:8084/employees/delete/byId',{
        params: {
          id: eid
        }
      }).then(function(){
        res.redirect("/employee/list")
       
    }) 
});





       
       
 



      


module.exports=router;