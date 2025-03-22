import React, { useState, } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { Logout } from '../firebase/firebaseMethods';

export default function SideBar() {
  const [showName, setShowName] = useState(false);
  const [showLogoutDialog, setShowLogoutDialog] = useState(false);
  const navigate = useNavigate();
  
  const handleLogoutClick = () => {
    setShowLogoutDialog(true);
  };

  const handleConfirmLogout = async () => {
   
      await Logout();
      navigate('/');
      setShowLogoutDialog(false);
    
  };

  const handleCancelLogout = () => {
    setShowLogoutDialog(false);
  };


  const icons =  [
    {
      name: 'Home',
      path: 'home',
      svg: (
        <svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 20 20' className='h-6 w-6'>
          <path fillRule='evenodd' d='M9.293 2.293a1 1 0 0 1 1.414 0l7 7A1 1 0 0 1 17 11h-1v6a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1v-3a1 1 0 0 0-1-1H9a1 1 0 0 0-1 1v3a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1v-6H3a1 1 0 0 1-.707-1.707l7-7Z' className="fill-current" />
        </svg>
      ),
    },
    {
      name: 'Search',
      path: 'search',
      svg: (
        <svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 20 20' className='h-6 w-6'>
          <path fillRule='evenodd' d='M9 3.5a5.5 5.5 0 1 0 0 11 5.5 5.5 0 0 0 0-11ZM2 9a7 7 0 1 1 12.452 4.391l3.328 3.329a.75.75 0 1 1-1.06 1.06l-3.329-3.328A7 7 0 0 1 2 9Z' className="fill-current" />
        </svg>
      ),
    },
    {
      name: 'Notifications',
      path: 'notifications',
      svg: (
        <svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 20 20' className='h-6 w-6'>
          <path fillRule='evenodd' d='M10 2a6 6 0 0 0-6 6c0 1.887-.454 3.665-1.257 5.234a.75.75 0 0 0 .515 1.076 32.91 32.91 0 0 0 3.256.508 3.5 3.5 0 0 0 6.972 0 32.903 32.903 0 0 0 3.256-.508.75.75 0 0 0 .515-1.076A11.448 11.448 0 0 1 16 8a6 6 0 0 0-6-6ZM8.05 14.943a33.54 33.54 0 0 0 3.9 0 2 2 0 0 1-3.9 0Z' className="fill-current" />
        </svg>
      ),
    },
    {
      name: 'Add Post',
      path: 'add',
     svg: <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
      <path strokeLinecap="round" strokeLinejoin="round" d="M12 9v6m3-3H9m12 0a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
    </svg>

    },
    {
      name: 'Chats',
      path: 'chats',
      svg: (
        <svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 20 20' className='h-6 w-6'>
          <path fillRule='evenodd' d='M10 3c-4.31 0-8 3.033-8 7 0 2.024.978 3.825 2.499 5.085a3.478 3.478 0 0 1-.522 1.756.75.75 0 0 0 .584 1.143 5.976 5.976 0 0 0 3.936-1.108c.487.082.99.124 1.503.124 4.31 0 8-3.033 8-7s-3.69-7-8-7Zm0 8a1 1 0 1 0 0-2 1 1 0 0 0 0 2Zm-2-1a1 1 0 1 1-2 0 1 1 0 0 1 2 0Zm5 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2Z' className="fill-current" />
        </svg>
      ),
    },
    {
      name: 'My Space',
      path: 'profile',
      svg: (
        <svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 20 20' className='h-6 w-6'>
          <path fillRule='evenodd' d='M18 10a8 8 0 1 1-16 0 8 8 0 0 1 16 0Zm-5.5-2.5a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0ZM10 12a5.99 5.99 0 0 0-4.793 2.39A6.483 6.483 0 0 0 10 16.5a6.483 6.483 0 0 0 4.793-2.11A5.99 5.99 0 0 0 10 12Z' className="fill-current" />
        </svg>
      ),
    },
  ];

  return (
    <div className={`fixed  h-screen rounded-md ${showName ? 'w-screen' : ''}`}> {/* Added flex class here */}
      <div
        className={`h-screen sticky top-0 flex flex-col justify-between transition-all duration-500 ease-in-out ${showName ? 'w-full items-start bg-gradient-to-r from-black to-black/0' : 'w-20 bg-black'}`} // Transition added here
      >
        
        <div className='w-20 mt-5 '>
          <div className='text-center   bg-gradient-to-r from-violet-500 to-orange-600 bg-clip-text text-transparent text-2xl'>
            echo
          </div>
        </div>
        <div className='w-20' onMouseEnter={() => setShowName(true)} onMouseLeave={() => setShowName(false)}>
          <div className='flex flex-col items-center h-full'>
            {icons.map((icon, index) => (
              <div key={index} className='relative h-16 flex items-center'>
                <NavLink
                  to={icon.path}
                  className={({ isActive }) =>
                    `h-8 w-8 flex items-center justify-center no-underline ${isActive ? 'text-white fill-white' : 'text-gray-600 fill-gray-400'}`
                  }
                >
                  {icon.svg}
                </NavLink>
                {showName && (
                <NavLink
                  to={icon.path}
                  className={({ isActive }) =>
                    `absolute left-10 top-1/2 transform -translate-y-1/2 rounded px-2 py-1 text-sm whitespace-nowrap z-10 no-underline bg-gray-800 ${isActive ? 'text-white' : 'text-gray-400'}`
                  }
                >
                  {icon.name}
                </NavLink>
              )}
              </div>
            ))}
          </div>
        </div>
        <div className='w-20 flex flex-col items-center mb-5'>
          <button className='text-red-500' onClick={handleLogoutClick}>Log Out</button>
        </div>
      </div>
      <div className='flex-grow'></div>
      {showLogoutDialog && (
        <div className="fixed top-0 left-0 w-full h-full bg-black/50 flex items-center justify-center z-50"> {/* Overlay */}
          <div className="bg-white p-6 rounded-md">
            <p>Are you sure you want to log out?</p>
            <div className="mt-4 flex justify-end">
              <button className="bg-gray-200 px-4 py-2 rounded-xl mr-2" onClick={handleCancelLogout}>Cancel</button>
              <button className="bg-red-500 text-white px-4 py-2 rounded-xl" onClick={handleConfirmLogout}>Log Out</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}