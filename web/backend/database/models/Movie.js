const mongoose = require('mongoose')
const {Schema}=mongoose

let movieSchema=new mongoose.Schema({
    name:{
        type:String,
        trim:true,
        required:true
    },
    rating:{
        type:String,
        trim:true,
        required:true  
    },
    watched:{
        type:Boolean,
        required:true
    }
    

})

const Movie=mongoose.model('Movie',movieSchema)

module.exports=Movie