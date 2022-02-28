const express=require('express');
const router=express.Router();
const axios=require('axios');
const bodyparser=require('body-parser');
var JSAlert = require("js-alert"); 
const app=express();
app.use(bodyparser.json());




//home page
const controller=require('../controller/imsController');
const { json } = require('express/lib/response');

//myprofile 

router.get('/myprofile:id',(req,res)=>{
  axios.get('http://localhost:8084/ims/getuser/byid',{
    params:{
      id:req.params.id
    }
  }).then(function(user){
    res.render("myProfile",{"user":user.data})
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
   
      res.render("redirect",{'user':user.data});
    
    
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

router.get('/reg',(req,res)=>{
  res.render('registration')
})
router.post('/register',(req,res)=>{
  controller.register(req,res);
})


router.get('/edittimetable:branch/:year/:sem',(req,res)=>{
  
  
    axios.get('http://localhost:8084/ims/admin/get/timetable/byweek',{
        params: {
          branch:req.params.branch,
          year:req.params.year,
          sem:req.params.sem
        }
      }).then(function(time_table){
        
        res.render("editTimeTable",{'time_table':time_table.data})
       
     })
})


router.get('/changepasswordrequest',(req,res)=>{
  res.render('editPassword')
})
router.post('/changepassword:id',(req,res)=>{
  let pid=req.params.id
  let oPaswd=req.body.oldPassword
  let nPaswd=req.body.newPassword
 console.log(pid)
 console.log(oPaswd)
 console.log(nPaswd)
  
  axios.get('http://localhost:8084/ims/changepassword',{
    params:{
      id:req.params.id,
      oldPassword:req.body.oldPassword,
      newPassword:req.body.newPassword
    }
  }
    
  ).then(function(){
    
    res.redirect("/ims/home")
   
 })
  });


       
 



      


module.exports=router;