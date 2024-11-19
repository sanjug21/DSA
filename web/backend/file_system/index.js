
let fs=require("fs")

// console.log(fs);


// CRUD


// Create

// let data='I created this file using file system'
// let data2="I created another file using file sytem using another approach"
// fs.writeFile('abc.txt',data,{
//     encoding:'utf-8',
//     flag:'w'
// },(err)=>{
//     if(err){
//         throw err
//     }
//     console.log("file created")

// })

//or

// fs.writeFileSync('abc.txt',data2)

//-------------------------------------------------------------------

//READ

// fs.readFile('abc.txt',{
//     encoding:'utf-8',
//     flag:'r'
// },(err,data)=>{
//         if(err){
//         throw err
//     }
//     console.log(data)
// })

// or
// let data=fs.readFileSync("abc.txt")
// console.log(data.toString())



//---------------------------------------------------------------------

// UPDATE

// fs.appendFile('abc.txt'," mene file update ki",(err)=>{
//             if(err){
//         throw err
//     }
//     console.log("updated")
// })

//or
//fs.appendFileSync('abc.txt'," mene firse file update ki")



// --------------------------------------------------------------------------------------------------------

//DELETE

// fs.unlink('abc.txt',(err)=>{
//                 if(err){
//         throw err
//     }
//     console.log("Deleted")
// })

//or
// fs.unlinkSync("abc.txt")


