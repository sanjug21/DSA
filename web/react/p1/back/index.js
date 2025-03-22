const express=require('express')
const path=require('path')
const app=express()
const mongoose=require('mongoose')


app.use(express.urlencoded({extended:true}))

mongoose.connect('mongodb+srv://Sanju:Sanju@cluster0.y9iuq.mongodb.net/Echos').then(()=>{
    console.log("Ban gaya database");
    
}).catch((e)=>{
    console.log("Koi Error Aa Gaya");
    
})



app.get('/',(req,res)=>{
    res.send("hello")
})

app.listen(4000,()=>{
    console.log("Server chal gaya");
    
})