//make component using ReactDOm

// let rootEle=document.getElementById("root");

// let h1=document.createElement('h1');
// h1.innerText="Welcome to 2025";

// //need to append h1 



// rootEle.append(h1);



//create comp using React only
let rootEle=document.getElementById("root");
let h1=React.createElement('h1',{id:"san",className:"haikuch"},"welcome to 2025");
let root=ReactDOM.createRoot(rootEle);
root.render(h1);

