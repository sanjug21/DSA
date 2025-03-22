// all apis takes an array of Promises and they can be fetch also


const p1=new Promise((resolve,reject)=>{
    // setTimeout(()=>resolve("P1 success"),3000)
    setTimeout(()=>reject("P1 failed"),3000)
})
const p2=new Promise((resolve,reject)=>{
    //   setTimeout(()=>resolve("P2 success"),1000)
      setTimeout(()=>reject("P2 failed"),1000)
})

const p3=new Promise((resolve,reject)=>{
    // setTimeout(()=>resolve("P3 success"),2000)
     setTimeout(()=>reject("P2 failed"),2000)
})


// Promise.all  --> make api calls parally and if error occurs it will return immidiately and throw an error and won't wait for other promises


 Promise.all([p1,p2,p3]).then((res)=>{console.log(res)}).catch((err)=>{console.log(err)})


// Promise.allSettled  ---> it will wait for all the apicalls to be settled even if an error occurs and will return an array can contain error if occured

 Promise.allSettled([p1,p2,p3]).then((res)=>{console.log(res)}).catch((err)=>{console.log(err)})


// Promise.race  ---> apicall which settled first it will return the first  resolved value or even error if error occured

 Promise.race([p1,p2,p3]).then((res)=>{console.log(res)}).catch((err)=>{console.log(err)})


// Promise.any  --> wait for first success then it will return its resolved value and if all promises fails then it will throw an aggrefated error:All promises were rejected / which is an array of errors

Promise.any([p1,p2,p3]).then((res)=>{console.log(res)}).catch((err)=>{
    console.log(err) 
    console.log(err.errors)})


