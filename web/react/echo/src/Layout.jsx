import React from 'react';
import { Outlet } from 'react-router-dom';
import SideBar from './components/SideBar';

export default function Layout() {
  return (
    <div className="w-screen h-screen bg-black flex items-start flex-row"> 
      <SideBar />
      <div className='ml-20 w-screen h-full'>
      <Outlet />
      </div>
    </div>
  );
}