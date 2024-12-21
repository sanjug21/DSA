const express=require('express')
const router=express.Router()
const {Zoo}=require('../models/Zoo')
const ZooDetails = require('../models/Zoo')

//task 1 show all zoos
router.get('/',async(req,res)=>{
   let allZoo=await ZooDetails.find({})
 
    // console.log(allZoo);
    
    res.render('show',{allZoo})
})

// task 2 show add a zoo page
router.get('/new',(req,res)=>{
    res.render('new')
})

// task 3 add data to firebase
router.post('/',async(req,res)=>{
    let {zooName,
        numberOfAnimal,
        location,
        openingDuration,
        zooImageUrl}=req.body
    await ZooDetails.insertMany([{"zooName":zooName,"numberOfAnimal":numberOfAnimal,"location":location,"openingDuration":openingDuration,"zooImageUrl":zooImageUrl}])
    // console.log(req.body)
    res.redirect('/')
})

// task 4 to show details about specific
router.get('/:id',async(req,res)=>{
    await ZooDetails.findById(req.params.id).then((i)=>{
        res.render('details',{i})
    }).catch((e)=>{})
    
})

// task 5 to show edit page
router.get('/editpage/:id',async(req,res)=>{
    await ZooDetails.findById(req.params.id).then((i)=>{
        res.render('edit',{i})
    }).catch((e)=>{})
})


// task 6 to update the details
router.patch('/edit/:id',async(req,res)=>{
    let {zooName,
        numberOfAnimal,
        location,
        openingDuration,
        zooImageUrl}=req.body
    await ZooDetails.findByIdAndUpdate(req.params.id,{"zooName":zooName,"numberOfAnimal":numberOfAnimal,"location":location,"openingDuration":openingDuration,"zooImageUrl":zooImageUrl}).then((data)=>{
        res.redirect('/')
    }).catch((e)=>{

    })
    

})

// task 7 to delete a zoo

router.delete('/delete/:id',async(req,res)=>{
    console.log(req.params.id);
    
    await ZooDetails.findByIdAndDelete(req.params.id).then(()=>{
        res.redirect
        ('/')
    }).catch((e)=>{

    })
    
})




module.exports=router