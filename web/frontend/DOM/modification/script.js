var h1=document.querySelector("h1");
h1.style.backgroundColor="red"
h1.style.fontSize="50px"

h1.style.cssText=`
    color: coral;
    font-size: 70px;
    background-color:cyan;
`


//  text content is brainless it doesn't read css
console.log(h1.innerText)
console.log(h1.textContent)


// setters
h1.innerHTML="<Strong>help</Strong>"
console.log(h1.innerHTML)



var anch=document.querySelector("a")
var link=anch.getAttribute('href')
console.log(link)

anch.setAttribute("href","https://www.google.co.in/")
console.log(anch.getAttribute('href'))


let image=[
    
]

let img=document.querySelector("img")
let i=0;
const inter=setInterval(()=>{
    
    img.setAttribute("href",image[i%3])
    i++
},2000)
clearInterval(inter)