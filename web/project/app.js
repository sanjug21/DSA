const express =require('express')
const app=express()
const path=require('path')
const mongoose = require('mongoose');
const methodoverride=require('method-override')
const seedDB = require('./seed')
const productRoutes = require('./routes/product')
const reviewRoutes = require('./routes/review')
const ejsMate=require('ejs-mate')
const flash = require('connect-flash');
const session = require('express-session');


mongoose.connect('mongodb://127.0.0.1:27017/E-commerce').then(()=>{
    console.log("database ban gaya")
}).catch((e)=>{
    console.log(e)

})

let configSession = {
    secret: 'keyboard cat',
    resave: false,
    saveUninitialized: true
}

app.engine('ejs' , ejsMate);
app.set('views',path.join(__dirname,'views'))
app.set('view engine','ejs')
app.use(express.static(path.join(__dirname,'public')))
app.use(express.urlencoded({extended:true}))
app.use(methodoverride('_method'))
app.use(session(configSession)); 
app.use(flash());
app.use((req,res,next)=>{
    res.locals.success = req.flash('success');
    res.locals.error = req.flash('error');
    next();
})





// seedDB()


app.use(productRoutes); 
app.use(reviewRoutes);



// video 59
// git link 
// https://github.com/Samarth0606/LiveJan23/blob/main/Lecture-56/app.js
// 57






app.listen(3000,()=>{
    console.log("Chal gaya server");
    
})