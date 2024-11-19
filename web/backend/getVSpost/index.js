const express=require('express');
const app=express();
const path=require('path')

app.set('view engine','ejs');
app.set('views',path.join(__dirname,"views"))
app.use(express.static(path.join(__dirname,"public")))

 app.use(express.urlencoded({extended:true})) 
app.use(express.json())


app.get('/',(req,res)=>{
    res.render('index')
})

// get request ko handle kr raha h
app.get('/user',(req,res)=>{
    let {Name,Age}=req.query
    res.send(`${Name} ${Age}`)
})


// post request ko handle kr raha h

app.post('/user',(req,res)=>{
    console.log(req.body);
    
    res.send("Post kr dia")
})














app.listen(8080,()=>{})