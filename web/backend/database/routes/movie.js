const express=require('express')
const Movie = require('../models/Movie')
const router=express.Router()



// task 1 ---> view all movies
router.get('/',async (req,res)=>{
    let allMovies=await Movie.find({})
    res.render('home',{allMovies})
})

// task 2 ---> new Page
router.get('/new',(req,res)=>{
    res.render('new')
})

//task 3 --> to add data to database
router.post('/',async(req,res)=>{
    let {name,price,image,description}=req.body
    const data = [ { name: name, price: price, image: image, description: description} ];
    await Product.insertMany(data)
    res.redirect('/')
})


module.exports=router