import React, { useContext, useState } from 'react';
import echo from '../images/icon.jpg';
import UserContext from '../context/UserContext';
import Image from '../images/p.png';

function NavBar({}) {
  const { pic, name } = useContext(UserContext); 
  
  const [menuOpen, setMenuOpen] = useState(false);
  const [searchOpen, setSearchOpen] = useState(false);

  const toggleMenu = () => {
    setMenuOpen(!menuOpen);
  };

  const toggleSearch = () => {
    setSearchOpen(!searchOpen);
  };

  return (
    <div className="w-screen h-14 bg-gradient-to-r from-orange-600 to-violet-500 flex justify-between items-center pl-2 relative">
      <div className="flex items-center">
        <img 
          src={pic === "" ? Image : pic} 
          alt="Profile Picture" 
          className="w-12 h-12 rounded-full object-cover" 
        />
        <div className="w-2" /> 
        <pre className="text-black text-lg">{`Welcome, ${name}`}</pre>
      </div>

      <div className="flex items-center">
        <div className="hidden lg:flex items-center">
          <button>Home</button>
          <div className="w-4" />
          <button>Profile</button>
          <div className="w-4" />
          <button>Notifications</button>
          <div className="w-4" />
          <button>Log Out</button>
          <div className="w-4" />
        </div>

        <div className="flex items-center">
          <input 
            type="text" 
            placeholder="Search..." 
            className="p-2 px-4 rounded-3xl outline-none focus:border-orange-500 w-64 hidden md:block" 
          />
          <div className="w-2" />
          <img src={echo} alt="Echo Logo" className="h-14 hidden lg:block" /> 
        </div>

        <div className="lg:hidden flex items-center">
          <button className="p-2" onClick={toggleMenu}>
            <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16m-7 6h7"></path>
            </svg>
          </button>
        </div>
      </div>

      {menuOpen && (
        <div className="absolute top-14 right-0 bg-white shadow-lg rounded-lg p-4 flex flex-col items-start lg:hidden">
          <button className="w-full text-left mb-2">Home</button>
          <button className="w-full text-left mb-2">Profile</button>
          <button className="w-full text-left mb-2">Notifications</button>
          <button className="w-full text-left mb-2">Log Out</button>
        </div>
      )}
    </div>
  );
}
export default NavBar;
