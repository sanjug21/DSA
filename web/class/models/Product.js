const mongoose = require('mongoose')
const {Schema}=mongoose

let productSchema=new mongoose.Schema({
    name:{
        type:String,
        trim:true,
        required:true
    },
    price:{},
    image:{
        type:String,
        trim:true,
        required:true
    },
    description:{
        type:String,
        trim:true,
        required:true
    }
    

})

const Product=mongoose.model('Product',productSchema)

module.exports=Product