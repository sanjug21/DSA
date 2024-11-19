const express=require('express');
const app=express();
const path=require('path')

app.set('view engine','ejs');
app.set('views',path.join(__dirname,"views"))
app.use(express.static(path.join(__dirname,'public')))

app.use(express.urlencoded({extended:true}))
// dummy array
let comments=[
    {
        id:0,
        name:"Sanju",
        comment:"kuch nh"
    },
    {
        id:1,
        name:"Shubham",
        comment:"syatem"
    },
    {
        id:2,
        name:"Ritu",
        comment:"Me nh to kon be"
    }
]

app.get('/',(req,res)=>{
    res.send("he he chal gaya")
})


// task 1 ---> display all the elements in list
app.get('/blogs',(req,res)=>{
    res.render('index',{comments})
})


// task 2 ---> show a form to create new blog
app.get('/blog/new',(req,res)=>{
    res.render('new')
})

// task 3 ---> to add data to db

app.post('/blogs',(req,res)=>{
    let {Name,comment}=req.body
    comments.push({
        name:Name,
        comment:comment,
        id:comments.length
    })
    
    res.redirect('/blogs')
})

// task 4 ---> show info about one blog

app.get('/blogs/:id',(req,res)=>{
    let {id}=req.params
    
    
    let comment=comments.find(cmt=>cmt.id == id)

     res.render('show',{comment})
})


app.listen(8080,()=>{
    console.log("Chal gaya server");
    
})