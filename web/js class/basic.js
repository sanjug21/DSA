
// // foreach, map, filter
// let arr=[10,20,30,40,50];
// arr.forEach(function(item,index){
//     console.log(index,item);
// })
// let newArr=arr.map(function(item,index){
//     return item*2;
// })
// console.log(arr,newArr)

// let filterArr=arr.filter(function(item,index){
//     if(item>30)return true;
// })
// console.log(filterArr)


//------------------------------------------------------------------------------------------------------------------------------------------------


// closures
/* whenever you return a function that function is never rerturned alone it always takes along the lexical environment with itso that if in future 
 we ever want to run that returned function then it should not throw an error

 use case of closure
 before the class santax was introduced we had no way to privatise our variable or functions so in that case we used closures so that we can 
 privatised the variable
*/

// function outer(){
//     let a=100;
//     let b=200;
//     function inner(){
//         console.log(a)
//     }
//     inner();
// }
// let out=outer();
// out()



//------------------------------------------------------------------------------------------------------------------------------------------------


// function counter(){
//     let count=0;
//     return{
//         getCount: function(){
//             console.log(count)
//         },
//         increment: function(){
//             count++;
//         },
//         decrement: function(){
//             count--;
//         }
//     };
// }
// let cout=counter();
// cout.increment();
// cout.getCount();
// console.log(cout)



//------------------------------------------------------------------------------------------------------------------------------------------------


// //Prototype
// // denderProto
// let obj={
//     a:23,
//     b:"Sanju",
//     c:function(){
//         console.log("Singh")
//     }
// }
// console.log(obj.b)
// console.log(obj.c())
// let ar=[10,20,30];

// // normal function
// function sam(){

// }
// let oob=sam()
// console.log(oob)



//------------------------------------------------------------------------------------------------------------------------------------------------



// //constructor function
// function sam(){
// this.naam="cashew"
// this.age=2
// // in constuctor function we never return anything
// }
// let ob=new sam()
// console.log(ob)


//------------------------------------------------------------------------------------------------------------------------------------------------


// //arraow funvtion
// let sum=(a,b)=>{return a+b};

// let sum2=(x,y,z)=> x+y+z;

// let sqr=a=> a*a
// let ans=sqr(6)

// let obj1={
//     a:"sanju",
//     b: function(){
//         return (this)
//     }
// }

// let answ=obj1.b
// ans()

// let obj2={
//     a:10,
//     fn:function(){
//         function sam(){
//             console.log(this)
//         }
//         let a=sam()
//         return a
//     }
// }
// let answer=obj2.fn()
// answer()

console.log("start");
setTimeout(function(){console.log("after 4 sec")},4*1000)
console.log("end")

//Prototype
// denderProto
let obj={
    a:23,
    b:"Sanju",
    c:function(){
        console.log("Singh")
    }
}
console.log(obj.b)
console.log(obj.c())
let ar=[10,20,30];

// normal function
function sam(){

}
let oob=sam()
console.log(oob)


//constructor function
function sam(){
this.naam="cashew"
this.age=2
}
let ob=new sam()
console.log(ob)


