const arr=[1,2,5,7,9,15];

const maping=arr.map((item)=>item*2)

 const filtr=arr.filter(function even(item){
    return item % 2 === 0;
 })

 const reduce=arr.reduce(function red(acc,curr){
    acc=acc+curr;
    return acc;
 })



console.log(arr)
console.log(maping)
console.log(filtr)
console.log(reduce)