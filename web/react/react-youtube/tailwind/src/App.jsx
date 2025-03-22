import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Card from './component/card'
function App() {
  const [count, setCount] = useState(0)
  let myObj={
    name:"Sanju",
    age:"21"
  }
  let arr=[1,2,3,4];

  return (
    <>
      <h1 className='bg-green-400 rounded-xl '>Tailwind Test</h1>
      <Card name="Sanju "/>
      <Card name="Shubham"/>
      <Card  ar={arr}/>
    </>
  )
}

export default App
