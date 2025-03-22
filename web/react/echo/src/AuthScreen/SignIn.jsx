import React, { useState } from 'react';
import { Link } from 'react-router-dom'; 
import './signin.css';
import { Login }from '../firebase/firebaseMethods'


export default function SignInPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false); 
  const [showPassword, setShowPassword] = useState(false); 

  const emailController = (event) => {
    setEmail(event.target.value);
  };

  const passwordController = (event) => {
    setPassword(event.target.value);
  };

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const login = async (event) => { 
    event.preventDefault();
    try {
      setIsLoading(true); 
      await Login(email,password);
    }
    catch (error) {
      alert(error.message);
    }
    finally {setIsLoading(false); }
    
  };

  return (
    <div className="p-5 main  relative bg-cover bg-center bg-no-repeat"> 
      {isLoading && ( 
        <div className="fixed top-0 left-0 w-full h-full bg-black/50 flex items-center justify-center z-50">
          <div className="loader"></div> 
        </div>
      )} 
      <div className="flex flex-col lg:flex-row   sm:flex-col justify-evenly items-center h-screen"> 
        <div className="hidden sm:hidden lg:block icon bg-cover bg-center bg-no-repeat w-1/3 h-2/3 p-4 rounded-lg border-2 border-red-400 min-h-80">
          <h1 className='flex items-start justify-center mt-16 text-5xl bg-gradient-to-r from-violet-500 to-orange-600 bg-clip-text text-transparent'>Welcome to</h1> 
        </div>
        <h1 className='mt-10 text-3xl sm:text-5xl bg-gradient-to-r from-violet-500 to-orange-600 bg-clip-text text-transparent lg:hidden '>
          Welcome to Echo</h1> 
        <div className="create w-full sm:w-34 md:w-3/4 lg:w-1/3 h-2/3 p-4 flex flex-col justify-center rounded-lg border-2 border-red-400 bg-gradient-to-r from-violet-400 to-orange-400 min-h-80"> 
          <input 
            className=' border-2 border-cyan-500 rounded-3xl p-1 pl-3 pr-3 w-full outline-none focus:border-orange-500' 
            type="email" 
            placeholder="Enter your email" 
            value={email} 
            onChange={emailController} 
          />
          <br />
          <div className="relative">
            <input 
              className='border-2 border-cyan-500 rounded-3xl p-1 pl-3 pr-3 w-full outline-none focus:border-orange-500' 
              type={showPassword ? 'text' : 'password'} 
              placeholder="Enter your password" 
              value={password} 
              onChange={passwordController} 
            />
            
            <span 
                className="absolute right-3 top-2 text-gray-500 cursor-pointer" 
                onClick={togglePasswordVisibility}
              >
                {showPassword ?
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" className="size-5 ">
                <path fillRule="evenodd" d="M3.28 2.22a.75.75 0 0 0-1.06 1.06l14.5 14.5a.75.75 0 1 0 1.06-1.06l-1.745-1.745a10.029 10.029 0 0 0 3.3-4.38 1.651 1.651 0 0 0 0-1.185A10.004 10.004 0 0 0 9.999 3a9.956 9.956 0 0 0-4.744 1.194L3.28 2.22ZM7.752 6.69l1.092 1.092a2.5 2.5 0 0 1 3.374 3.373l1.091 1.092a4 4 0 0 0-5.557-5.557Z" clipRule="evenodd" />
                <path d="m10.748 13.93 2.523 2.523a9.987 9.987 0 0 1-3.27.547c-4.258 0-7.894-2.66-9.337-6.41a1.651 1.651 0 0 1 0-1.186A10.007 10.007 0 0 1 2.839 6.02L6.07 9.252a4 4 0 0 0 4.678 4.678Z" />
              </svg>              
              : 
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" className="size-5">
              <path d="M10 12.5a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5Z" />
              <path fillRule="evenodd" d="M.664 10.59a1.651 1.651 0 0 1 0-1.186A10.004 10.004 0 0 1 10 3c4.257 0 7.893 2.66 9.336 6.41.147.381.146.804 0 1.186A10.004 10.004 0 0 1 10 17c-4.257 0-7.893-2.66-9.336-6.41ZM14 10a4 4 0 1 1-8 0 4 4 0 0 1 8 0Z" clipRule="evenodd" />
              </svg>

              } 
              </span>
          </div>
          <br />
          <button className='border-2 rounded-3xl border-orange-800 bg-orange-500 h-8 text-white ' onClick={login}>Login</button> 
          
          <br /><br />
          <div className='text-center'>Forget password?</div>
          <br/>
          <div className='border-t-2 border-orange-400'></div>
          <br/><br />
          
          <Link to="/signup" className='rounded-3xl  bg-green-500 h-8 text-white text-center '>Create Account</Link> 
     
        </div>
      </div>
    </div>
  );
}