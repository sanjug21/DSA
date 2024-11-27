const express=require('express')
const Product = require('../models/product')
const router=express.Router()



// task 1 ---> view all products
router.get('/',async (req,res)=>{
    let allProducts=await Product.find({})
    res.render('home',{allProducts})
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