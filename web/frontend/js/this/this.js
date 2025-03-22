"use strict";

// this in global scope

console.log(this)


//  this inside a function

function x(){
    // value depends on strictmode /non strict mode
    console.log(this)
}
x()

// this inside non-strict mode (THIS SUBSTITUTION)--->

//if the value of this keyword is null or undefined then 
//this keyword will be replaced with global objects only in non strict mode

// this keyword value depends upon how it is called
x()  
window.x()


// this inside a objects method

const obj={
    name:"sanju",
    a:10,
    y: function(){
        console.log(this.name)
    }
}
obj.y()
const obj2={
    name:"Shubham",
}

obj.y.call(obj2)


// this inside arrow function  -->based enclosing lexical context

const object={
    a:5,
    x:()=>{console.log(this)}
}
object.x()

const object2={
    a:5,
    x:function(){
        const y=()=>{console.log(this)}
        y()
    }
}
object2.x()


// this in dom -->reference to object HTMLElement

