import { signOut, signInWithEmailAndPassword, createUserWithEmailAndPassword } from "firebase/auth";
import { Auth, Db, Storage } from "./firebase";
import { ref, uploadBytes, getDownloadURL } from "firebase/storage";
import { doc, setDoc, getDoc, onSnapshot, getFirestore, collection, query, where, getDocs, serverTimestamp, updateDoc, arrayUnion, arrayRemove, startAfter, limit, orderBy } from "firebase/firestore";
import { v4 as uuidv4 } from 'uuid';

const storageRefPic = ref(Storage, "profilePics"); 
const storageRefPost = ref(Storage, "Post");

async function Login(email, password) {
  try {
    await signInWithEmailAndPassword(Auth, email, password);
  } catch (error) {
    if (error.code === "auth/invalid-credential") {
      throw new Error("Invalid credential.");
    } else {
      throw new Error("An error occurred during login try again later.");
    }
  }
}

async function Signup(name, email, password, img) {
  try {
    const { user } = await createUserWithEmailAndPassword(Auth, email, password);
    let imageUrl = "";

    if (img) {
      const imageRef = ref(storageRefPic, user.uid);
      const snapshot = await uploadBytes(imageRef, img);
      imageUrl = await getDownloadURL(snapshot.ref);
    }

    const userDoc = {
      uid: user.uid,
      username: name.toLowerCase(),
      name: name,
      email: email,
      phone: null,
      bio: "Radhe Radhe",
      pic: imageUrl,
      notification: [],
      followers: [],
      followings: [],
      posts: [],
      blocked: [],
      isOnline: true,
      createdAt: serverTimestamp(),
    };

    await setDoc(doc(Db, "Users", user.uid), userDoc);
  } catch (error) {
    throw error;
  }
}

async function Logout() {
  try {
    await signOut(Auth);
  } catch (error) {
    if (error.code) {
      throw new Error(`Logout failed: ${error.code}`);
    } else {
      throw new Error("Logout failed: An unknown error occurred.");
    }
  }
}

async function GetUserDetails(uid) {
  try {
    const docSnap = await getDoc(doc(Db, "Users", uid));
    if (docSnap.exists()) {
      return docSnap.data();
    } else {
      throw new Error(`User with UID ${uid} not found.`);
    }
  } catch (error) {
    console.error("Error fetching user details:", error);
    return null;
  }
}

function StreamUserDetails(uid, onUserUpdate, onError) {
  try {
    const userRef = doc(Db, "Users", uid);

    const unsubscribe = onSnapshot(
      userRef,
      (docSnapshot) => {
        if (docSnapshot.exists()) {
          const userData = docSnapshot.data();
          onUserUpdate(userData);
        } else {
          onUserUpdate(null);
        }
      },
      (error) => {
        onError(error);
        console.error("Error streaming user details:", error);
      }
    );

    return unsubscribe;
  } catch (error) {
    onError(error);
    console.error("Error setting up stream:", error);
    return () => { };
  }
}

async function searchUsersByUsername(username) {
  try {
    const usersRef = collection(getFirestore(), "Users");
    const q = query(
      usersRef,
      where("username", ">=", username.toLowerCase()),
      where("username", "<=", username.toLowerCase() + "\uf8ff")
    );

    const querySnapshot = await getDocs(q);
    const users = [];
    querySnapshot.forEach((doc) => {
      users.push(doc.data());
    });
    return users;
  } catch (error) {
    console.error("Error searching users:", error);
    return [];
  }
}

async function addPost(userId, name, pic, description, file) {
  try {
    let url = "";
    const postId = uuidv4();

    if (file) {
      const mediaRef = ref(storageRefPost, `${postId}/${name}`);
      const snapshot = await uploadBytes(mediaRef, file);
      url = await getDownloadURL(snapshot.ref);
    }

    const postDoc = {
      postId: postId,
      uid: userId,
      name: name,
      pic: pic,
      description: description,
      url: url,
      createdAt: serverTimestamp(),
      likes: [],
      comments: [],
    };

    const postsCollection = collection(Db, "Posts");
    await setDoc(doc(postsCollection, postId), postDoc);

    await addPostToUser(userId, postId);

    return "Done";

  } catch (error) {
    console.error("Error adding post:", error);
    throw error;
  }
}

async function addPostToUser(userId, postId) {
  try {
    const userRef = doc(Db, "Users", userId);
    await updateDoc(userRef, {
      posts: arrayUnion(postId),
    });
  } catch (error) {
    console.error("Error adding post ID to user:", error);
    throw error;
  }
}

function getPosts(pageSize = 15, lastDocument = null, onPostsReceived, onError) {
  try {
    const postsRef = collection(Db, "Posts");
    let q = query(postsRef, orderBy("createdAt", "desc"), limit(pageSize));

    if (lastDocument) {
      q = query(postsRef, orderBy("createdAt", "desc"), startAfter(lastDocument), limit(pageSize));
    }

    const unsubscribe = onSnapshot(q, (querySnapshot) => {
      const posts = querySnapshot.docs.map((doc) => ({
        id: doc.id,
        ...doc.data(),
      }));

      const newLastDocument = querySnapshot.docs.length > 0 ? querySnapshot.docs[querySnapshot.docs.length - 1] : null;

      onPostsReceived(posts, newLastDocument);
    }, (error) => {
      onError(error);
      console.error("Error streaming posts:", error);
    });

    return unsubscribe;
  } catch (error) {
    onError(error);
    console.error("Error setting up stream:", error);
    return () => { };
  }
}

async function followUser(currentUserId, targetUserId) {
  try {
    await updateDoc(doc(Db, "Users", currentUserId), {
      followings: arrayUnion(targetUserId),
    });

    await updateDoc(doc(Db, "Users", targetUserId), {
      followers: arrayUnion(currentUserId),
    });
  } catch (error) {
    console.error("Error following user:", error);
    throw error;
  }
}

async function unfollowUser(currentUserId, targetUserId) {
  try {
    await updateDoc(doc(Db, "Users", currentUserId), {
      followings: arrayRemove(targetUserId),
    });

    await updateDoc(doc(Db, "Users", targetUserId), {
      followers: arrayRemove(currentUserId),
    });
  } catch (error) {
    console.error("Error unfollowing user:", error);
    throw error;
  }
}


export { Login, Signup, Logout, GetUserDetails, StreamUserDetails, searchUsersByUsername, addPost, addPostToUser, getPosts, followUser, unfollowUser };