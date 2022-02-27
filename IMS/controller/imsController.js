const express=require('express');
const router=express.Router();
const axios=require('axios');
function insertRecord(req, res) {
    
    axios({
        method: 'post',
        url: 'http://localhost:8084/ims/add',
        data: {
          name: req.body.name,
          email:req.body.email,
          phoneNo:req.body.phoneNo,
          userId:req.body.userId,
          password:req.body.password,
          branch:req.body.branch,
          type:req.body.role
        }
      }).then(function(users){
        console.log(users.data);
        res.redirect("/ims/home");
    })     
      
};

function insertSubject(req, res) {
    
  axios({
      method: 'post',
      url: 'http://localhost:8084/ims/admin/add/subject',
      data: {
        name: req.body.name,
        branch:req.body.branch,
        subjectCode:req.body.subjectCode,
        sem:req.body.sem,
        year:req.body.year

        
      }
    }).then(function(subject){
      console.log(subject.data);
      res.redirect("/ims/home");
  })     
    
};


function insertTimeTable(req, res) {
  
  axios({
      method: 'post',
      url: 'http://localhost:8084/ims/admin/add/timetable',
      data: 
        [
          {
        day:req.body.day,
        time:req.body.time1,
        branch:req.body.branch,
        year:req.body.year,
        sem:req.body.sem,
        subjectCode:req.body.subject1,
        userId:req.body.user1

      },
      {
        day:req.body.day,
        time:req.body.time2,
        branch:req.body.branch,
        year:req.body.year,
        sem:req.body.sem,
        subjectCode:req.body.subject2,
        userId:req.body.user2

      },
      {
        day:req.body.day,
        time:req.body.time3,
        branch:req.body.branch,
        year:req.body.year,
        sem:req.body.sem,
        subjectCode:req.body.subject3,
        userId:req.body.user3

      },
      {
        day:req.body.day,
        time:req.body.time4,
        branch:req.body.branch,
        year:req.body.year,
        sem:req.body.sem,
        subjectCode:req.body.subject4,
        userId:req.body.user4

      }
    ]
       

        
      
    }).then(function(timetable){
      
      res.redirect("/ims/addrequest");
  })     
    
};



function updateUser(req,res,uid) {
   

    axios({
        method: 'put',
        url: 'http://localhost:8084/ims/edit/user',
        data: {
          id:uid,
          
          name: req.body.name,
          email:req.body.email,
          phoneNo:req.body.phoneNo,
          userId:req.body.userId,
          password:req.body.password,
          branch:req.body.branch,
          type:req.body.role

        }
      }).then(function(){
        res.redirect("/ims/home");
      });
};

function updateSubject(req,res,sid) {
  

  axios({
      method: 'put',
      url: 'http://localhost:8084/ims/admin/edit/subject',
      data: {
        id:sid,
        name:req.body.name,
        subjectCode:req.body.subjectCode,
        branch:req.body.branch,
        year:req.body.year,
        sem:req.body.sem
       

      }
    }).then(function(){
      res.redirect("/ims/allsubject");
    });
};






module.exports={insertRecord,insertTimeTable,insertSubject,updateUser,updateSubject};