
const express=require('express')
const app=express()

const port=3000

app.use((req,res,next)=>{
    console.log("mai hoon middleware");
    
    // res.send('<p>chal gaya middleware</p>')
    next()
})

app.get('/', (req, res) => {
    res.send('Hello World!')
  })




app.listen(port,()=>{
    console.log(`server chal gaya ${port} par`);
    
})


