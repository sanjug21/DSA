
const express=require('express')
const app=express()

const port=3000

app.listen(port,()=>{
  console.log(`server chal gaya ${port} par`);
  
})



// Middleware =run everytime when use or browser makes a request //or run on every incoming request

// app.use('/middle',(req,res,next)=>{
//     console.log("mai hoon middleware");
    
//     //  res.send('<p>chal gaya middleware</p>')
//   next()
// })




app.get('/', (req, res) => {
  res.send('Hello World!')
  })



  //Path    subreddit
  // app.get('/r/:sanju', (req, res) => {
  //   console.log(req.params);
  
  //   let {sanju}=req.params
  //   res.send(`hello ${sanju}`)
  //   })


  //Query
  app.get('/search', (req, res) => {
    console.log(req.query);
    console.log(req.query.search);
    let {search}=req.query
    console.log(search);
    res.send(search)
    })



  //bad request  always put in last after all routes
  app.get('*', (req, res) => {
    res.send('Bad request')
  })







