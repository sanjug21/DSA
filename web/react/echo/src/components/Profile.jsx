import React, { useState, useEffect, useContext } from 'react';
import { GetUserDetails } from './firebase/firebaseMethods';
import DefImage from '../images/p.png';
import UserContext from '../context/UserContext'; // Import your user context

function Profile({ userId }) { // userId prop for searched user, if any
    const currentUser = useContext(UserContext); // Get the currently logged-in user
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isCurrentUserProfile, setIsCurrentUserProfile] = useState(false); // Add this state

    useEffect(() => {
        const fetchUserData = async () => {
            setLoading(true);
            setError(null);

            try {
                const userData = await GetUserDetails(userId);
                setUser(userData);

                // Check if it's the current user's profile
                if (currentUser && userData && currentUser.uid === userData.uid) {
                    setIsCurrentUserProfile(true);
                } else {
                    setIsCurrentUserProfile(false);
                }

            } catch (err) {
                console.error("Error fetching user data:", err);
                setError("Error loading profile. Please try again later.");
            } finally {
                setLoading(false);
            }
        };

        fetchUserData();
    }, [userId, currentUser]); // Add currentUser to dependency array

    if (loading) {
        return <div className="text-center">Loading profile...</div>;
    }

    if (error) {
        return <div className="text-red-500 text-center">{error}</div>;
    }

    if (!user) {
        return <div className="text-center">User not found.</div>;
    }

    const imageSrc = user.pic || DefImage;

    return (
        <div className="container mx-auto p-4">
            <div className="flex flex-col items-center">
                <img
                    src={imageSrc}
                    alt={`${user.name}'s profile picture`}
                    className="w-32 h-32 rounded-full object-cover mb-4"
                    onError={(e) => { e.target.src = DefImage; }}
                />
                <h2 className="text-2xl font-bold mb-2">{user.name}</h2>
                <p className="text-gray-700 mb-4">@{user.username}</p>

                {/* Conditionally render edit button */}
                {isCurrentUserProfile && (
                    <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                        Edit Profile
                    </button>
                )}

                {/* Display other user details */}
                <p className="mb-2">{user.bio}</p>
                {/* ... other details ... */}

                {/* Conditionally render follow/unfollow button */}
                {!isCurrentUserProfile && (
                    <button className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
                        {/* Logic to determine Follow/Unfollow text */}
                        {/* For example: If user is followed, display "Unfollow", else display "Follow" */}
                        Follow
                    </button>
                )}

            </div>
        </div>
    );
}

export default Profile;