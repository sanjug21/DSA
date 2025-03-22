import React from 'react';
import "../AuthScreen/SignIn"

function LoadingSpinner() {
    return (
      <div className="flex items-center justify-center h-screen bg-gradient-to-r from-violet-500 to-orange-600"> 
        <div className="loader"></div> 
      
      </div>
    );
  }

export default LoadingSpinner;