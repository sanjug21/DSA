import React from 'react';
import { NavLink } from 'react-router-dom';
import { FaUserCircle, FaStickyNote, FaListAlt, FaCalendarAlt, FaAddressBook } from 'react-icons/fa';

function SideBar() {
  const navItems = [
    { to: '/notes', icon: <FaStickyNote />, label: 'Notes' },
    { to: '/todos', icon: <FaListAlt />, label: 'Todos' },
    { to: '/events', icon: <FaCalendarAlt />, label: 'Events' },
    { to: '/contacts', icon: <FaAddressBook />, label: 'Contacts' },
  ];

  const navLinkClass = ({ isActive }) =>
    isActive
      ? 'bg-blue-500 text-white p-2 rounded flex items-center'
      : 'p-2 rounded hover:bg-gray-300 flex items-center';

  return (
    <div className="h-screen w-48 bg-slate-200 p-4">
      <div className="flex items-center mb-4">
        <FaUserCircle className="text-2xl mr-2" />
        <span className="text-lg font-bold">My Space</span>
      </div>
      <div className="flex flex-col space-y-2">
        {navItems.map((item) => (
          <NavLink
            key={item.to}
            to={item.to}
            className={navLinkClass}
            aria-label={item.label}
          >
            {item.icon}
            <span className="ml-2">{item.label}</span>
          </NavLink>
        ))}
      </div>
    </div>
  );
}

export default SideBar;