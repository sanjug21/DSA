import { useCallback, useState, useEffect,useRef } from 'react'

import './App.css'

function App() {
  const [length,setLength]=useState(8);
  const [numAllowed,setNum]=useState(false);
  const [charAllowed,setChar]=useState(false);
  const [pass,setPass]=useState("");

// ref hook
const passRef=useRef(null)

  const generator=useCallback(()=>{
    let password=""
    let str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    if(numAllowed)str+="0123456789";
    if(charAllowed)str+="!@#$%^&*~"
    for(let i=1;i<=length;i++){
          let ch=Math.floor(Math.random()*str.length+1);
          password+=str.charAt(ch)
        }
        setPass(password);
  },[length,numAllowed,charAllowed,setPass])

  const copyPassToClip=useCallback(()=>{
    passRef.current?.select()
    passRef.current?.setSelectionRange(0,3)
    window.navigator.clipboard.writeText(pass)
  },[pass])

  useEffect(()=>{
    generator()
  },[length,numAllowed,charAllowed,generator])

  return (
    <>
   
      <div className='w-full max-w-md mx-auto shadow-md rounded-lg px-4 my-8 text-orange-400 bg-gray-500'>
      <div className='text-white text-center my-3'>Password Generator</div>
      <div className='flex shadow rounded-lg overflow-hidden mb-4'>
        <input type="text" className='w-full py-1 px-3 ' value={pass} placeholder='password' readOnly ref={passRef}/>
       <button className='bg-blue-200 text-black'
       onClick={copyPassToClip}
       >Copy</button>
      </div>
      <div className='flex text-sm gap-x-2'>
        <div className=' flex items-center gap-x-1'>
          <input type="range" min={6}max={100} value={length} className='cursor-pointer'
          onChange={(e)=>{setLength(e.target.value)}}
          />
          <label htmlFor="">length: {length}</label>
        </div>
        <div className='flex items-center gap-x-1'>
          <input type="checkbox" 
          defaultChecked={numAllowed}
          onChange={()=>setNum((prev)=>!prev)}
          />
          <label htmlFor="">Numbers</label>
        </div>
        <div className='flex items-center gap-x-1'>
          <input type="checkbox" 
          defaultChecked={charAllowed}
          onChange={()=>setChar((prev)=>!prev)}
          />
          <label htmlFor="">Character</label>
        </div>

      </div>
      

       
     </div>

        
    </>
  )
}

export default App
