// Before ES6 (2015), JavaScript variables had only Global Scope and Function Scope.

// ES6 introduced two important new JavaScript keywords: let and const.

// These two keywords provide Block Scope in JavaScript.

// Variables declared inside a { } block cannot be accessed from outside the block:


// 1. Global Scope
// 2. Functional Scope 
// 3. Block Scope or Script

// var a=10;
// function x(){
//   var b=10;
//   function y(){
//     console.log(b)
//   }
//   y();
// }
// x()


var a=10;
function san(){
    var b=20;
    console.log(a);
  
}


{
  const c=30;
}

console.log(a);
san()
console.log(c);
console.log(b);
