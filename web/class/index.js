const express=require('express')
const app=express()
const path=require('path')
const mongoose = require('mongoose');
const productRoute=require('./routes/product')



mongoose.connect('mongodb://127.0.0.1:27017/window').then((data)=>{console.log("db connected");
}).catch((err)=>{console.log('db connect nh hua')})




app.set('view engine','ejs')
app.set('views',path.join(__dirname,'views'))
app.use(express.static(path.join(__dirname,'public')))

app.use(express.urlencoded({extended:true}))


// modal is a java script class which acts like a collection and we can not create modal directly we need a schema
// MVC  ---> Modal view controller



app.use(productRoute,()=>{

})








app.listen(3000,()=>{console.log("chal gaya server");
})
