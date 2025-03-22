

// event Listners

// function attachEventListner(){

//     let count=0
// document.getElementById("clickMe").
// addEventListener("click",function xyz(){
//     console.log("Button clicked",++count)
// })
// }

// attachEventListner()



// Promise ---> it is an object representing the eventual completion or failure of an asynchronous operation


const git_api="https://api.github.com/users/sanjug21"

const user=fetch(git_api);
// console.log(user)


user.then(function ok(data){
    return data.json()
}).then(
    (data)=>{
         console.log(data)
    }
)

