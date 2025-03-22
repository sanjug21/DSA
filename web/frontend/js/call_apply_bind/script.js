//  call

let name={
    name:"sanju",
    age:22,
    print:function(){
        console.log(this.name+" "+this.age)
    }
}
// name.print();

let name2={
    name:"Ritu",
    age:16,
    print:function(){
        console.log(this.name+" "+this.age)
    }
}

// name2.print.call(name2)

let printDetails=function(Gender){
                 console.log(this.name+" "+this.age+" "+Gender)
                    }
printDetails.call(name,"male")
printDetails.call(name,"female")

// apply  ---> we pass parameters in a array

printDetails.call(name,["male","mathura"])
printDetails.call(name,["female","mathura"])


// bind
let print=printDetails.bind(name,"male");
console.log(print);
print()