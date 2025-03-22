function customRender(reactEle,root){
    // const domElement=document.createElement(reactEle.type);
    // domElement.innerHTML=reactEle.children;
    // domElement.setAttribute('href',reactEle.props.href)
    // domElement.setAttribute('target',reactEle.props.target)
    // root.appendChild(domElement)

    const domElement=document.createElement(reactEle.type);
    domElement.innerHTML=reactEle.children;
    for (const prop in reactEle.props) {
        domElement.setAttribute(prop,reactEle.props[prop])
    }
    root.appendChild(domElement)
}


const reactEle={
    type:'a',
    props:{
        href:'https://www.google.co.in/',
        target:'_blank'
    },
    children:'Click me to visit'
}

const root=document.getElementById("root")

customRender(reactEle,root)