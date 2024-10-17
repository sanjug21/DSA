let val=require("./app")
let {obj,naam}=require("./app")

console.log(val.obj.name)
console.log(obj,naam)

// when we don't export anything from file and still that file is require by another file then by default it exports an empty object