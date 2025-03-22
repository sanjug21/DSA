

import { useEffect, useState } from 'react'
import './App.css'
import { ThemeProvider } from './context/Theme'
import ThemeButton from './components/ThemeButton'
import Card from './components/Card'

function App() {
  const [theme,setTheme]=useState("light")
  const lightTheme=()=>{
    setTheme("light")
  }
  const darkTheme=()=>{
    setTheme("dark")
  }

  useEffect(()=>{

    document.querySelector('html').classList.remove("light","dark")
    document.querySelector('html').classList.add(theme)

  },[theme])

  return (
    
   <ThemeProvider value={{theme,lightTheme,darkTheme}}>
    
    <ThemeButton />
    <Card />
   </ThemeProvider>

  )
}

export default App
