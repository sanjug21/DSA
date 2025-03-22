import React, { useState } from 'react';
import './signin.css';
import profilePic from "../images/p.png"
import {Link} from "react-router-dom"

import { Signup } from '../firebase/firebaseMethods';



const SignUpPage = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    password: '',
    confirmPassword: '',
});
const [profileImage, setProfileImage] = useState(null);
const [showPassword, setShowPassword] = useState(false);
const [isLoading, setIsLoading] = useState(false);
const [errors, setErrors] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
}); 

const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
    setErrors({ ...errors, [name]: '' }); 
};

const handleImageChange = (event) => {
  setProfileImage(event.target.files[0]); 
};

const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
};

const signUp = async (event) => {
    event.preventDefault();
    
    setErrors({}); 
     
        let hasErrors = false;
        const newErrors = { ...errors }; 
        if (!formData.name || formData.name.length < 3 || formData.name.length > 30) {
            newErrors.name = "Name must be between 3 and 50 characters";
            hasErrors = true;
        }
        if (!formData.email || !isValidEmail(formData.email)) {
            newErrors.email = "Invalid email address";
            hasErrors = true;
        }
       
        if (!formData.password || formData.password.length < 8 || formData.password.length > 20) {
            newErrors.password = "Password must be between 8 and 20 characters";
            hasErrors = true;
        }
        if (formData.password !== formData.confirmPassword) {
            newErrors.confirmPassword = "Passwords do not match";
            hasErrors = true;
        }

        if (hasErrors) {
            setErrors(newErrors);
            setIsLoading(false);
            return; 
        }
        console.log(profileImage)
        try {  
          setIsLoading(true);
          await Signup(formData.name,formData.email,formData.password,profileImage);
        }
        catch (error) {
          alert(error.message);
        }
        finally{ setIsLoading(false);}
       
};


// email and phone validation
const isValidEmail = (email) => {
     const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
};


  return (
    <div className="main p-5 h-auto  bg-cover bg-center bg-no-repeat "> 
      {isLoading && ( 
        <div className="fixed top-0 left-0 w-full h-full bg-black/50 flex items-center justify-center z-50">
          <div className="loader"></div> 
        </div>
      )} 
      <div className='flex flex-col items-center '>
        <h1 className='mt-10 text-5xl bg-gradient-to-r from-violet-500 to-orange-600 bg-clip-text text-transparent'>Welcome to Echo</h1> 
        <br />


        <form onSubmit={signUp} className='p-4 w-full sm:w-3/4 md:w-1/2 ' noValidate> 
          <div 
          className="p-4  flex flex-col justify-center items-center rounded-lg border-2 border-red-400 bg-gradient-to-r from-violet-400 to-orange-400"> 
            <div className='relative'>
              <div className='profile border-2 h-56 w-56 rounded-full border-black' >
                {profileImage ? (
                  <img src={URL.createObjectURL(profileImage)} alt="Profile" className="rounded-full object-cover w-full h-full" />
                ) : (
                  <img src={profilePic} alt="Default Profile" className="rounded-full object-cover w-full h-full" /> 
                )}
              </div>
              <label htmlFor="file-upload" className="absolute bottom-1 right-0 h-6 w-16 rounded-full  flex justify-center items-center text-center bg-gradient-to-r from-blue-500 to-purple-500">change</label>
              <input type="file" id="file-upload" onChange={handleImageChange} className="hidden" />
            </div>
            <br /> 
            <input 
              className={`border-2 border-cyan-500 rounded-3xl p-1 pl-3 pr-3 w-full outline-none focus:border-orange-500 ${errors.name ? 'border-red-500' : 'border-cyan-500'}` } 
              type="text" 
              name="name" 
              placeholder="Enter your name" 
              value={formData.name} 
              onChange={handleChange} required 
              
            />
            {errors.name && <div className="text-red-500 text-sm mt-1 ">{errors.name}</div>}

            <br />
            <input 
              className={`border-2 border-cyan-500 rounded-3xl p-1 pl-3 pr-3 w-full outline-none focus:border-orange-500 ${errors.email ? 'border-red-500' : 'border-cyan-500'}`} 
              type="email" 
              name="email" 
              placeholder="Enter your email" 
              value={formData.email} 
              onChange={handleChange} required 
             
            />
            {errors.email && <div className="text-red-500 text-sm mt-1">{errors.email}</div>}

            
            <br />
            <div className="relative w-full">
              <input 
                className={`border-2 border-cyan-500 rounded-3xl p-1 pl-3 pr-3 w-full outline-none focus:border-orange-500 ${errors.password ? 'border-red-500' : 'border-cyan-500'}`} 
                type={showPassword ? 'text' : 'password'} 
                name="password" 
                placeholder="Create password" 
                value={formData.password} 
                onChange={handleChange} required 
              />
              <span 
                className="absolute right-3 top-2 text-gray-500 cursor-pointer" 
                onClick={togglePasswordVisibility}
              >
                {showPassword ?
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" className="size-5">
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
            {errors.password && <div className="text-red-500 text-sm mt-1">{errors.password}</div>}

            <br />
            <input 
              className={`border-2 border-cyan-500 rounded-3xl p-1 pl-3 pr-3 w-full outline-none focus:border-orange-500 ${errors.confirmPassword ? 'border-red-500' : 'border-cyan-500'}`} 
              type="password" 
              name="confirmPassword" 
              placeholder="Confirm password" 
              value={formData.confirmPassword} 
              onChange={handleChange} required 
            />
            {errors.confirmPassword && <div className="text-red-500 text-sm mt-1">{errors.confirmPassword}</div>}

           <br />
            <button 
              className='border-2 rounded-3xl border-orange-800 bg-orange-500 h-8 text-white w-2/3' 
              type="submit" 
            >
              Create Account
            </button> 
            <br /> 
            <div className='flex '>
              <div >Already have an Account? </div>
              <pre> 
                <Link to="/"> Sign In</Link> 
              </pre>
             
                  
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default SignUpPage;