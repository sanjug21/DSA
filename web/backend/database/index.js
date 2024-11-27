const { log } = require('console');
const express=require('express')
const app=express()
const mongoose = require('mongoose');
const path=require('path')
const movieRoute=require('./routes/movie')


mongoose.connect('mongodb://127.0.0.1:27017/movies').then(()=>{
    console.log("Database bana diya")
}).catch((e)=>{
    console.log("koi error aa gyaa")
})

app.set('view engine','ejs');
app.set('views',path.join(__dirname,'views'))
app.use(express.static(path.join(__dirname,"public")))


app.use(movieRoute,()=>{

})







app.listen(3000,()=>{
    console.log("Chal gaya server")
})
