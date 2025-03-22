import React, { useContext } from 'react';
import UserContext from '../context/UserContext';
import DefImage from '../images/p.png';

function Profile() {
  const userData = useContext(UserContext);
  
  return (
    <div className='w-full p-2'>
      <div className='flex space-x-4'>
        <img
          src={userData?.pic === "" ? DefImage : userData?.pic}
          alt={userData?.name ? `${userData.name}'s profile picture` : "Profile picture"}
          className='w-40 h-40 rounded-full object-cover'
        />
        <div className='flex flex-col w-11/12 flex-grow pt-2 pb-2 space-y-2'>
          <div className='text-4xl truncate overflow-hidden whitespace-nowrap'>
            {userData?.name}
          </div>
          <div>
            <div className='line-clamp-3 max-w-96'>
              {userData?.bio}
             </div>
          </div>
        </div>
      </div>
      
    </div>
  );
}

export default Profile;