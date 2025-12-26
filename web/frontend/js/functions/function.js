// Function statement /  Function declaration
function a(){
    console.log("a called")
}
a()
// b()

// Function Expression  

var b=function (){
    console.log("b called")
}
b()
// difference b/w statement and expression is of hoisting when a is called it executes but throws an error b is not a function  if called before initialization.

// -----------------------------------------------------------------------------------------------------------------------

//Anonymous Function ---> a function without name is called as anonymous function and they are used when used as variables 
// function (){

// }
 

//Named function Expression  ---> function with a name


// -------------------------------------------------------------------------------------------------------------------------------------

// First class functions  --> ability to use function as values or pass as arguments or can be returned from a function

let c=function (){
    console.log("c is assigned")
}


// Arrow function



// --------------------------------------------------------------------------------------------------------------------------------------------------

// Call Back functions  ---> when a function is passed in another function then it is called as callback function

function x(y){
    y() // calling the function passed as argument
}

x(function y(){console.log("this is a callback function")})


// ------------------------------------------------------------------------------------------------------------------------------------------------

// Constructor Function

class A{
    constructor(name,age){
        this.name=name
        this.age=age
    }
}

class B extends A {
    constructor(name,age,Gender) {
        super(name,age);
        this.Gender=Gender
    }
}

var people=new B("Sanju",22,"Male")
console.log(people)

// Higher Order Function  ---> function which takes function as an argument or returns a function is called as higher order function

function x(){
    console.log("hello")
}
y(x)
function y(x){
    x()
}
