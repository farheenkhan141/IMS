

const express=require('express');
const morgan=require('morgan');
const path=require('path');
const ejs=require('ejs');
const bodyparser=require('body-parser');
const dotenv=require('dotenv');

const app=express();
//const router=require('./controller/employeeController');
const router=require('./router/router');

//log request
app.use(morgan('tiny'));

app.use('/assets', express.static('assets'));


//add bodyparser
app.use(bodyparser.urlencoded({
    extended:true
}));
app.use(bodyparser.json());

//Importing PORT from config file
dotenv.config({ path: 'config.env' })
PORT=process.env.PORT || 8080
app.listen(PORT, () => { console.log(`Server is running on http://localhost:${PORT}`) });


app.set('views',path.join(__dirname,'/views/'));
app.set('view engine', 'ejs');



app.use('/ims',router);
