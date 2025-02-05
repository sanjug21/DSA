const express =require('express')
const app=express()
const path=require('path')
const mongoose = require('mongoose');
const methodoverride=require('method-override')
const seedDB = require('./seed')
const productRoutes = require('./routes/product')
const reviewRoutes = require('./routes/review')
const ejsMate=require('ejs-mate')


mongoose.connect('mongodb://127.0.0.1:27017/E-commerce').then(()=>{
    console.log("database ban gaya")
}).catch((e)=>{
    console.log(e)

})

app.engine('ejs' , ejsMate);
app.set('views',path.join(__dirname,'views'))
app.set('view engine','ejs')
app.use(express.static(path.join(__dirname,'public')))
app.use(express.urlencoded({extended:true}))
app.use(methodoverride('_method'))








// seedDB()
app.use(productRoutes); 
app.use(reviewRoutes);








app.listen(3000,()=>{
    console.log("Chal gaya server");
    
})