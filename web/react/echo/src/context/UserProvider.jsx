import React, { useState, useEffect, useContext } from 'react';
import  AuthContext  from './AuthContext';
import  UserContext  from './UserContext';
import { StreamUserDetails } from '../firebase/firebaseMethods';

const UserProvider = ({ children }) => {
  const { user } = useContext(AuthContext);
  const [userData, setUserData] = useState(null);
  const [userLoading, setUserLoading] = useState(true);

  useEffect(() => {
    let unsubscribe;

    if (user) {
      setUserLoading(true);
      unsubscribe = StreamUserDetails(user.uid, (data) => {
        setUserData(data);
        setUserLoading(false);
      }, (error) => {
        console.error("Error streaming user data:", error);
        setUserData(null);
        setUserLoading(false);
      });
    } else {
      setUserData(null);
      setUserLoading(false);
    }

    return () => {
      if (unsubscribe) {
        unsubscribe();
      }
    };
  }, [user]);

  return (
    <UserContext.Provider value={{ userData, userLoading }}>
      {children}
    </UserContext.Provider>
  );
};

export default UserProvider;