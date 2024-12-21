const express=require('express');
const app=express()
const methodoverride=require('method-override')
const path=require('path')
const mongoose = require('mongoose');
const router = require('./routes/zoo');

mongoose.connect('mongodb://127.0.0.1:27017/zoo').then(()=>{
    console.log("database ban gaya")
}).catch((e)=>{
    console.log(e)
})

app.set('views',path.join(__dirname,'views'))
app.set('view engine','ejs')
app.use(express.static(path.join(__dirname,'public')))
app.use(express.urlencoded({extended:true}))
app.use(methodoverride('_method'))


app.use(router)

app.listen(3000,()=>{
    console.log("server chal gaya");
    
})