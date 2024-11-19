const exprss=require('express');
const app=exprss();
const path=require('path')

app.set('view engine','ejs');
app.set('views',path.join(__dirname,"views"))
// static file ko use karne do
app.use(exprss.static(path.join(__dirname,"public")))

//public folder keeps all the static files 


app.get('/',(req,res)=>{
    res.render('index')
})
















app.listen(8080,()=>{
    console.log("Chal gaya server");
    
})