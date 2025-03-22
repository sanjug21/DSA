import React, { useState } from 'react';
import SideBar from './components/SideBar';
import { Outlet } from 'react-router-dom';
import NavBar from './components/NavBar';

const Layout = () => {
   
    return (
        <div className="flex w-full">
           <SideBar/>
           <div className='w-screen '>

            <NavBar/>
            <Outlet />
           
           </div>
        </div>
    );
};

export default Layout;