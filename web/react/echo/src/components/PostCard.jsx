import React from 'react';
import DefImage from '../images/p.png';
import { FaHeart, FaComment } from 'react-icons/fa';

function PostCard({ post }) {
    if (!post) {
        return <div>Loading...</div>;
    }

    return (
        <div className='bg-gray-800 rounded-lg mb-4 w-[45%] space-y-1 shadow-2xl'>
            <div className="flex items-center mb-2 p-3 border-b border-black ">
                <img
                    src={post.pic}
                    alt={`${post.name}'s profile picture`}
                    className="w-10 h-10 rounded-full mr-2"
                    onError={(e) => { e.target.src = DefImage; }}
                />
                <span className="text-white text-xl">{post.name}</span>
            </div>
            <div className='p-3 flex'>
            {post.description && (
                <p className='text-white line-clamp-6'>{post.description}</p>
            )}
            </div>
            {post.url && (
                <div className="w-full rounded-lg overflow-hidden flex justify-center bg-slate-600 ">
                    <img
                        src={post.url}
                        alt="Post Image"
                        className="object-cover h-96"
                    />
                </div>
            )}
            <div className='border-t border-black p-3 flex justify-between items-center'>
                <div className="flex items-center">
                    <FaHeart className="mr-1 text-red-500" />
                    <div className='text-white self-center'>
                        {post.likes ? post.likes.length : 0}
                    </div>
                </div>
                <div className="flex items-center">
                    <FaComment className="mr-1 text-white" />
                    <div className='text-white self-center'>
                        {post.comments ? post.comments.length : 0}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default PostCard;