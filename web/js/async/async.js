
// async it always return a promise it returned value is not a promise then it wraps the return value into a promise

const p=new Promise(function(resolve,reject){
    setTimeout(()=>{resolve("resolved")},5000)
})


async function getData() {
    return p
}
const data=getData()
data.then((sol)=>console.log(sol))


// async and await  ---> await resolves the promise

async function handle(params) {
   const val= await p;
   console.log(val,"2")
   console.log("2nd executed")
}
handle()

// fetch -->it gives a response object when promise is resolved

api="https://api.github.com/users/sanjug21"

async function handleFetch(params) {
    const user=await fetch(api)
    const detail=await user.json()
    console.log(detail)
    
}
handleFetch()