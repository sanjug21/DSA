import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, NavLink } from 'react-router-dom';
import Profile from '../assets/p.png';
import axios from 'axios';

function ContactDetails() {
  const { id } = useParams();
  const [contact, setContact] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const [showDeleteConfirm, setShowDeleteConfirm] = useState(false);
  const [contactToDelete, setContactToDelete] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:5000/contacts/${id}`);
        setContact(response.data);
        setLoading(false);
      } catch (err) {
        setError(err);
        setLoading(false);
      }
    };

    fetchData();
  }, [id]);

  const handleDeleteClick = (contact) => {
    setContactToDelete(contact);
    setShowDeleteConfirm(true);
  };

  const closeDeleteConfirm = () => {
    setShowDeleteConfirm(false);
    setContactToDelete(null);
  };

  const deleteContact = async () => {
    try {
      await axios.delete(`http://localhost:5000/contacts/${id}`);
      navigate('/contacts');
      closeDeleteConfirm();
    } catch (err) {
      setError(err);
      closeDeleteConfirm();
    }
  };

  if (loading) {
    return (
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <div
          style={{
            border: '4px solid #f3f3f3',
            borderTop: '4px solid #3498db',
            borderRadius: '50%',
            width: '50px',
            height: '50px',
            animation: 'spin 2s linear infinite',
          }}
        ></div>
        <style>
          {`
            @keyframes spin {
              0% { transform: rotate(0deg); }
              100% { transform: rotate(360deg); }
            }
          `}
        </style>
      </div>
    );
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (!contact) {
    return <div>Contact not found.</div>;
  }

  return (
    <div className="p-5 space-y-2">
      <div className="flex justify-center items-center">
        <div className="w-50 h-50 rounded-full overflow-hidden">
          <img
            src={Profile}
            alt="Contact Profile"
            className="w-full h-full object-cover"
          />
        </div>
      </div>
      <div className="p-6 bg-white rounded-lg shadow-md hover:shadow-2xl">
        <h2 className="text-2xl font-semibold mb-4">Contact Information</h2>

        <div className="mb-4">
          <strong className="block text-gray-700 mb-1">Name:</strong>
          <p className="text-lg">{contact.name}</p>
        </div>

        <div className="mb-4">
          <strong className="block text-gray-700 mb-1">Phone:</strong>
          <p>{contact.phone}</p>
        </div>

        {contact.secondaryPhone && (
          <div className="mb-4">
            <strong className="block text-gray-700 mb-1">Secondary Phone:</strong>
            <p>{contact.secondaryPhone}</p>
          </div>
        )}

        <div className="mb-4">
          <strong className="block text-gray-700 mb-1">Email:</strong>
          <p>{contact.email}</p>
        </div>

        {contact.secondaryEmail && (
          <div className="mb-4">
            <strong className="block text-gray-700 mb-1">Secondary Email:</strong>
            <p>{contact.secondaryEmail}</p>
          </div>
        )}

        {contact.dob && (
          <div className="mb-4">
            <strong className="block text-gray-700 mb-1">Date of Birth:</strong>
            <p>{new Date(contact.dob).toLocaleDateString()}</p>
          </div>
        )}
        <div className="flex justify-end mt-4">
          <NavLink
            to={`/contacts/edit/${id}`}
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-lg mr-2"
          >
            Edit
          </NavLink>
          <button
            onClick={() => handleDeleteClick(contact)}
            className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded-lg"
          >
            Delete
          </button>
        </div>
      </div>
      {showDeleteConfirm && (
        <div className="fixed inset-0 flex items-center justify-center ">
          <div className="bg-white p-6 rounded-md shadow-lg">
            <p className="mb-4">
              Are you sure you want to delete {contactToDelete.name}?
            </p>
            <div className="flex justify-end space-x-4">
              <button
                className="bg-gray-300 hover:bg-gray-400 px-4 py-2 rounded-md"
                onClick={closeDeleteConfirm}
              >
                Cancel
              </button>
              <button
                className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-md"
                onClick={deleteContact}
              >
                Delete
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default ContactDetails;