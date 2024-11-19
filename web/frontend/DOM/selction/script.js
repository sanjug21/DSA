var tag=document.getElementsByTagName("h1");

console.log(tag)


var cls=document.getElementsByClassName("San");
console.log(cls)


var para=document.getElementById("para");
console.log(para)

// in query selector we can select id, class and element and select 1st occurence
var q_element=document.querySelector("h1")
console.log(q_element)

var q_class=document.querySelector(".San")
console.log(q_class)

var q_id=document.querySelector("#para")
console.log(q_id)




var qsa_id=document.querySelectorAll("#para")
console.log(qsa_id)

var qsa_class=document.querySelectorAll(".San")
console.log(qsa_class)

var qsa_element=document.querySelectorAll("div")
console.log(qsa_element)