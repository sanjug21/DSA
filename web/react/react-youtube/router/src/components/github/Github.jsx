import React, { useEffect, useState } from 'react'

function Github() {
    const [data,setData]=useState([])
    useEffect(()=>{
        fetch("https://api.github.com/users/sanjug21").then((res)=>res.json()).then((data)=>{
            setData(data)
        })
    },[])
  return (
    <>
    <div className='text-center m-4'>Github Followers:{data.followers}</div>
    <img src={data.avatar_url} alt="" height={300} width={300} />
    </>
  )
}

export default Github