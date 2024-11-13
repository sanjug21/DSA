let obj={
    name:"Sanju",
    age:20
}

let naam="Pushpendra"

// when we don't export anything from file and still that file is require by another file then by default it exports an empty object

//odule.exports={objj:obj}

// in es6 if key value are same
module.exports={obj,naam}