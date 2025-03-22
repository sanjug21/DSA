import React, { useState, useContext, useRef } from 'react';
import { addPost } from '../firebase/firebaseMethods';
import UserContext from '../context/UserContext';
import DefImage from '../images/p.png';
import 'react-toastify/dist/ReactToastify.css';
import { Alert, Snackbar } from '@mui/material';

function AddPost() {
    const currentUser = useContext(UserContext);
    const [description, setDescription] = useState('');
    const [file, setFile] = useState(null);
    const [previewURL, setPreviewURL] = useState(null);
    const [loading, setLoading] = useState(false);
    const [fileError, setFileError] = useState(null);
    const fileInputRef = useRef(null);
    const [alert, setAlert] = useState({ open: false, message: '', severity: 'info' });

    const showAlert = (message, severity = 'info') => {
        setAlert({ open: true, message, severity });
    };

    const handleCloseAlert = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setAlert({ ...alert, open: false });
    };

    const handleDescriptionChange = (e) => {
        setDescription(e.target.value);
    };

    const handleFileChange = (e) => {
        setFileError(null);
        const selectedFile = e.target.files[0];

        if (selectedFile) {
            if (selectedFile.type.startsWith("image/")) {
                setFile(selectedFile);
                const reader = new FileReader();
                reader.onloadend = () => {
                    setPreviewURL(reader.result);
                };
                reader.readAsDataURL(selectedFile);
            } else {
                setFileError("Please select an image file.");
                setFile(null);
                setPreviewURL(null);
                if (fileInputRef.current) {
                    fileInputRef.current.value = '';
                }
            }
        } else {
            setFile(null);
            setPreviewURL(null);
        }
    };

    const handleRemoveFile = () => {
        setFile(null);
        setPreviewURL(null);
        setFileError(null);
        if (fileInputRef.current) {
            fileInputRef.current.value = '';
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);

        if (!description.trim() && !file) {
            showAlert("Please write something or add an image.", 'error');
            setLoading(false);
            return;
        }

        try {
            const response = await addPost(currentUser.uid, currentUser.name, currentUser.pic, description, file);
            if (response === "Done") {
                showAlert("Post created successfully!", 'success');
                setDescription('');
                setFile(null);
                setPreviewURL(null);
                if (fileInputRef.current) {
                    fileInputRef.current.value = '';
                }
            }
        } catch (err) {
            console.log(err.message)
            showAlert("Error creating post. Please try again later.", 'error');
        } finally {
            setLoading(false);
        }
    };

    const imageSrc = currentUser?.pic || DefImage;

    return (
        <div className="container mx-auto h-screen w-full items-center justify-center flex bg-gradient bg-gradient-to-r from-violet-500 to-orange-500 ">
            {(
                <div className="border rounded-2xl p-3 shadow-2xl flex flex-col justify-between space-y-4 w-full max-w-screen-md bg-white">
                    <div className="flex items-center mb-2 p-2">
                        <img
                            src={imageSrc}
                            alt={`${currentUser?.name}'s profile picture`}
                            className="w-10 h-10 rounded-full mr-2"
                            onError={(e) => { e.target.src = DefImage; }}
                        />
                        <span className="text-black text-xl">{currentUser?.name}</span>
                    </div>

                    <div className='h-full flex flex-col justify-between'>
                        <textarea
                            placeholder="Write something..."
                            className="border rounded w-full p-2 mb-2 resize-none outline-none text-black focus:border-orange-500"
                            value={description}
                            onChange={handleDescriptionChange}
                            rows="3"
                        ></textarea>

                        <div className="mb-4 ">
                            {!previewURL && (
                                <div className="border rounded p-2 flex items-center justify-center cursor-pointer" onClick={() => fileInputRef.current.click()}>
                                    <label htmlFor="fileInput" className="cursor-pointer">
                                        <span className="text-2xl">+</span>&nbsp;
                                        <span>Add Image</span>
                                    </label>
                                    <input
                                        type="file"
                                        id="fileInput"
                                        className="hidden"
                                        onChange={handleFileChange}
                                        accept="image/*"
                                        ref={fileInputRef}
                                    />
                                </div>
                            )}

                            {fileError && <p className="text-red-500 mt-1">{fileError}</p>}

                            {previewURL && (
                                <div className='h-96 flex items-center justify-center bg-gray-800'>
                                    <img
                                        src={previewURL}
                                        alt="Preview"
                                        className="h-96 object-contain"
                                    />
                                </div>
                            )}
                        </div>

                    </div>
                    
                    <div className="flex justify-end"> 
                {loading ? (
                    <div className='flex space-x-4 mx-auto'><div className="animate-spin rounded-full h-8 w-8 border-t-4 border-l-4 border-orange-600 mx-auto"/>
                    <div>Posting...</div>
                    </div>
                ) : (
                    <button
                        type="submit"
                        className="bg-blue-500 hover:bg-blue-700 text-white py-2 px-4 rounded-2xl w-24"
                        
                        onClick={handleSubmit}
                    >
                        Post
                    </button>
                )}
                        {previewURL && !loading && (
                            <button
                                type="button"
                                className="bg-red-500 hover:bg-red-700 text-white py-2 px-4 rounded-2xl ml-2 w-24"
                                onClick={handleRemoveFile}
                            >
                                Remove
                            </button>
                        )}
                    </div>
                </div>
            )}
            <Snackbar open={alert.open} autoHideDuration={5000} onClose={handleCloseAlert} anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}>
                <Alert onClose={handleCloseAlert} severity={alert.severity} elevation={6} variant="filled">
                    {alert.message}
                </Alert>
            </Snackbar>
        </div>
    );
}

export default AddPost;


