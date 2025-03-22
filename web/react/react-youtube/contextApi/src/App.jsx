import UserContextProvider from "./context/UserContextProvider"
import Login from "./components/Login"
import Profile from "./components/profile"
function App() {
 

  return (
    <UserContextProvider>
     <h1>Learning context Api</h1>
      <Login/>
      <Profile />
    </UserContextProvider>
  )
}

export default App
