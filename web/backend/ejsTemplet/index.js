const express=require('express')
const app=express()
const path=require('path')

app.set('view engine','ejs') //view engine ko set kr dia taki templet dekh paye
app.set('views',path.join(__dirname,'views'))


app.get('/',(req,res)=>{
    res.render('index')
   
})

app.get('/random',(req,res)=>{
    let num=Math.floor(Math.random()*100)
    res.render('random',{num})
   
})

const todos=["hello","hola","namaste"]

app.get('/todo',(req,res)=>{
    res.render('todos',{todos})
   
})




app.listen(3000,()=>{
  console.log(`server chal gaya 3000 par`);
  
})

