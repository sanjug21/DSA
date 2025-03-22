import { useState } from 'react'
import { InputBox } from './components'
import useCuurencyInfo from './hooks/useCurrinfo'
import './App.css'

function App() {


  return (
    <>
    <h1 className='text-3xl bg-cyan-500 text-center'>Currency Converter</h1>
    <InputBox label="from" currencyOptions=""/>
    </>
  )
}

export default App
