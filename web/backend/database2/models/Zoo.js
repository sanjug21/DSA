const mongoose=require('mongoose')

const zooSchema=mongoose.Schema({
    zooName:{
        type:String,
        required:true,
        trim:true
    },
    numberOfAnimal:{
        type:Number,
        required:true,
        trim:true
    },
    location:{
        type:String,
        required:true,
        trim:true
    },
    openingDuration:{
        type:String,
        required:true,
        
    },
    zooImageUrl:{
        type:String,
        required:true,
        trim:true
    }
})

const ZooDetails=mongoose.model('zoo',zooSchema)

module.exports=ZooDetails