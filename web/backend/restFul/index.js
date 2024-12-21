const express=require('express');
const app=express();
const path=require('path')
const methodOverride=require('method-override')
const {v4: uuid}=require('uuid')


app.set('view engine','ejs');
app.set('views',path.join(__dirname,"views"))
app.use(express.static(path.join(__dirname,'public')))

app.use(methodOverride('_method'))//method overide for post to patch 
app.use(express.urlencoded({extended:true}))


// dummy array
let comments=[
    {
        id:uuid(),
        name:"Sanju",
        comment:"kuch nh"
    },
    {
        id:uuid(),
        name:"Shubham",
        comment:"syatem"
    },
    {
        id:uuid(),
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
        id:uuid()
    })
    
    res.redirect('/blogs')
})

// task 4 ---> show info about one blog

app.get('/blogs/:id',(req,res)=>{
    let {id}=req.params
    
    
    let comment=comments.find(cmt=>cmt.id == id)

     res.render('show',{comment})
})


// task 5 ---> to get the form for ediiting

app.get('/blogs/:id/edit',(req,res)=>{
    let {id}=req.params
    let comment=comments.find(cmt=>cmt.id == id)    
    res.render('edit',{comment})
})


// task 6 ---> actually edit the blog using patch not by using put
//we can not directly use patch in form so we have to use method overriding with npm
// 
app.patch('/blogs/:id',(req,res)=>{
    let {id}=req.params
    let foundComment=comments.find(cmt=>cmt.id == id) 
    let {comment}=req.body
    foundComment.comment=comment
    res.redirect('/blogs')
})

// task 7 ---> to delete a blog

app.delete('/blogs/:id',(req,res)=>{
    let {id}=req.params
    let newAr=comments.filter((cmt)=>{
        return cmt.id!=id
    })
    comments=newAr;
    res.redirect('/blogs')
})


app.listen(8080,()=>{
    console.log("Chal gaya server");
    
})