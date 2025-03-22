import React, { useState, useEffect,useContext } from 'react';
import { getPosts } from '../firebase/firebaseMethods';
import PostCard from '../components/PostCard';
import UserContext from '../context/UserContext';

function Home() {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [lastDocument, setLastDocument] = useState(null);
    const [hasMore, setHasMore] = useState(false);


    const userData = useContext(UserContext);

    useEffect(() => {
        const handlePostsReceived = (newPosts, newLastDocument) => {
            setPosts(newPosts);
            setLastDocument(newLastDocument);
            setHasMore(newLastDocument !== null);
            setLoading(false);
        };

        const handleError = (err) => {
            setError(err.message);
            setLoading(false);
        };

        const unsubscribe = getPosts(10, null, handlePostsReceived, handleError);

        return () => {
            if (unsubscribe) {
                unsubscribe();
            }
        };
    }, []);

    const loadMorePosts = () => {
        if (!hasMore || loading) return;

        setLoading(true);

        const handlePostsReceived = (newPosts, newLastDocument) => {
            setPosts([...posts, ...newPosts]);
            setLastDocument(newLastDocument);
            setHasMore(newLastDocument !== null);
            setLoading(false);
        };

        const handleError = (err) => {
            setError(err.message);
            setLoading(false);
        };

        getPosts(10, lastDocument, handlePostsReceived, handleError);
    };

    return (
        <div className='h-screen flex flex-col'>
           <div className='flex bg-white shadow-lg p-1'>
           <div className=' bg-gradient-to-r from-violet-500 to-orange-600 bg-clip-text text-transparent text-2xl'>
                {userData?.name ? `hello, ${userData.name}` : "Hello, User"}
            </div> 
           </div>
            
        <div className=' overflow-y-auto p-4 bg-slate-500 flex flex-col items-center'>
            {loading && <p>Loading posts...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            {posts.map((post) => (
                <PostCard key={post.postId} post={post} />
            ))}

            {hasMore && (
                <button onClick={loadMorePosts} disabled={loading}>
                    Show More
                </button>
            )}
            {!hasMore && posts.length > 0 && <p>No more posts to load.</p>}
            {!hasMore && posts.length === 0 && !loading && !error && <p>No posts yet.</p>}
        </div>
        </div>
    );
}

export default Home;