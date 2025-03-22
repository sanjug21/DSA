import { useEffect ,useState } from "react";

function useCuurencyInfo(Currency){
    const [data,setData]=useState({})
    useEffect(()=>{
        fetch(`https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/${Currency}.json`).then((res)=>res.json()).then((res)=>setData(res[Currency]))
    },[Currency])
    return data;
}

export default useCuurencyInfo;