import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  let [count,setCounter]= useState(10)
  
  const addValue=()=>{
    
    count++;
    setCounter(count);
    console.log(count);
  }
  const remValue=()=>{
    
    count--;
    setCounter(count);
    console.log(count);
  }
  return (
   
    <>
      <h1>We Are making a counter</h1>
      <p>Start value :{count}</p>
      <button onClick={addValue}>add val {count}</button>
      <br />
      <button onClick={remValue}>dec val {count}</button>
    </>
  )
  
}

export default App
