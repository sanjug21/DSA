import React, { useContext, useState, useEffect } from 'react';
import DefImage from '../images/p.png';
import UserContext from '../context/UserContext';
import { searchUsersByUsername, followUser, unfollowUser } from '../firebase/firebaseMethods';

export default function Search() {
  const userData = useContext(UserContext);
  const [searchUser, setSearchUser] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const imageSrc = userData?.pic || DefImage;

  const handleSearchChange = (e) => {
    setSearchUser(e.target.value);
  };

  useEffect(() => {
    const performSearch = async () => {
      if (searchUser.trim() === "") {
        setSearchResults([]);
        return;
      }

      setLoading(true);
      setError(null);

      try {
        const results = await searchUsersByUsername(searchUser);
        setSearchResults(results);
      } catch (err) {
        console.error("Search Error:", err);
        setError("Error performing search. Please try again later.");
        setSearchResults([]);
      } finally {
        setLoading(false);
      }
    };

    const timeoutId = setTimeout(() => {
      performSearch();
    }, 500);

    return () => clearTimeout(timeoutId);
  }, [searchUser]);

  const follow = async (user) => {
    try {
      await followUser(userData.uid, user.uid);
      setSearchResults(prevResults =>
        prevResults.map(u =>
          u.uid === user.uid ? { ...u, followers: [...(u.followers || []), userData.uid] } : u
        )
      );
    } catch (error) {
      console.error("Error following:", error);
      setError("Error following user. Please try again.");
    }
  };

  const unfollow = async (user) => {
    try {
      await unfollowUser(userData.uid, user.uid);
      setSearchResults(prevResults =>
        prevResults.map(u =>
          u.uid === user.uid ? { ...u, followers: (u.followers || []).filter(id => id !== userData.uid) } : u
        )
      );
    } catch (error) {
      console.error("Error unfollowing:", error);
      setError("Error unfollowing user. Please try again.");
    }
  };

  return (
    <div className='bg-gray-700 h-screen'>
      <div className='w-full h-16 bg-black flex items-center justify-between p-2'>
        <div className='flex items-center space-x-4'>
          <img
            src={imageSrc}
            alt={userData?.name ? `${userData.name}'s profile picture` : "Profile picture"}
            className='w-14 h-14 rounded-full object-cover'
            onError={(e) => { e.target.src = DefImage; }}
          />
          <div className='text-xl text-white'>
            {userData?.name ? `hello ${userData.name}` : "Hello, User"}
          </div>
        </div>
        <div className='w-1/3'>
          <input
            type="text"
            id="search-input"
            className='rounded-3xl w-full h-10 pl-4 pr-4 border-2 outline-none focus:border-orange-500 text-black placeholder:text-gray-500'
            placeholder='Search User'
            value={searchUser}
            onChange={handleSearchChange}
          />
        </div>
      </div>

      {loading && (
        <div className='flex justify-center items-center h-full'>
          <div className="animate-spin rounded-full h-8 w-8 border-t-4 border-l-4 border-orange-600"></div>
        </div>
      )}
      {error && <p className="text-red-500 text-center">{error}</p>}

      <div className="mt-4 p-4">
        {searchResults.length > 0 ? (
          <ul className="space-y-2">
            {searchResults.map((user) => (
              <li key={user.uid} className='rounded-2xl bg-white p-2 flex items-center justify-between'>
                <div className="flex items-center">
                  <img
                    src={user.pic || DefImage}
                    alt={`${user.name}'s profile picture`}
                    className='w-10 h-10 rounded-full object-cover mr-2'
                    onError={(e) => { e.target.src = DefImage; }}
                  />
                  <div>{user.name}</div>
                </div>
                <div>
                  {userData?.followings?.includes(user.uid) ? ( // Use userData directly
                    <button
                      className="bg-red-500 hover:bg-red-700 text-white py-2 px-4 rounded-2xl"
                      onClick={() => unfollow(user)}
                    >
                      Unfollow
                    </button>
                  ) : (
                    <button
                      className="bg-blue-500 hover:bg-blue-700 text-white py-2 px-4 rounded-2xl"
                      onClick={() => follow(user)}
                    >
                      Follow
                    </button>
                  )}
                </div>
              </li>
            ))}
          </ul>
        ) : (
          !loading && !error && searchUser.trim() !== "" ? (
            <p className="text-center text-white">No users found.</p>
          ) : null
        )}
      </div>
    </div>
  );
}