import { initializeApp } from "firebase/app";
import { getStorage, ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";
import { getFirestore } from "firebase/firestore";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyDwV4KkaU81IRhWYC7ms4dKH-ggJYSUkac",
  authDomain: "chatbot-84fc1.firebaseapp.com",
  projectId: "chatbot-84fc1",
  storageBucket: "chatbot-84fc1.appspot.com",
  messagingSenderId: "1068856645318",
  appId: "1:1068856645318:web:b3e9b447229948124ee9c7",
  measurementId: "G-GS1NHLSLBB"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const Auth=getAuth(app);
const Db = getFirestore(app);
const Storage=getStorage(app);
export {Auth, Db, Storage};
export default app;

