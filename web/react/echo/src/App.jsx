import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import SignInPage from "./AuthScreen/SignIn";
import SignUpPage from "./AuthScreen/SignUp";
import Home from "./tabs/Home";
import UserContext from './context/UserContext';
import { Auth } from './firebase/firebase';
import { StreamUserDetails} from "./firebase/firebaseMethods";
import LoadingSpinner from './components/LoadingSpinner';
import Layout from './Layout';
import Search from './tabs/Search';
import AddPost from './tabs/AddPost';
import Notification from './tabs/Notification';
import Chats from './tabs/Chats';
import Profile from './tabs/Profile';

function App() {
  const [User, setUser] = useState(null);
  const [userData, setUserData] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  
    useEffect(() => {
      const unsubscribe = Auth.onAuthStateChanged(async (authUser) => {
        setUser(authUser);
        setIsLoading(false);
      });
  
      return unsubscribe;
    }, []);

    useEffect(() => {
      let unsubscribe;
  
      if (User) {
        
        unsubscribe = StreamUserDetails(User.uid, (data) => {
          setUserData(data);
         
        }, (error) => {
          console.error("Error streaming user data:", error);
          setUserData(null);
          
        });
      } else {
        setUserData(null);
       
      }
  
      return () => {
        if (unsubscribe) {
          unsubscribe();
        }
      };
    }, [User]);
  
  

  return (
    
    <UserContext.Provider value={userData}> 
      <BrowserRouter>
        <Routes>
          <Route 
            path="/" 
            element={isLoading ? <LoadingSpinner /> : (User ?<Navigate to="/main" />: <SignInPage />)} 
          /> 
          <Route 
            path="/signup" 
            element={isLoading ? <LoadingSpinner /> : (User ? <Navigate to="/main" /> : <SignUpPage />)} 
          />
          

          <Route path="/main" element={User ? <Layout /> : <Navigate to="/" />}  >
            <Route index element={<Navigate to="home" />} /> 
            <Route path="home" element={<Home />} />  
            <Route path="search" element={<Search/>} />
            <Route path="add" element={<AddPost/>} />
            <Route path="notifications" element={<Notification/>} />
            <Route path="chats" element={<Chats/>} />
            <Route path="profile" element={<Profile/>} />

          </Route>
          
          <Route 
            path="*"
            element={<Navigate to="/" />}
          />
        </Routes>
      </BrowserRouter>
    </UserContext.Provider>
  );
}

export default App;
