import React, { useState, useEffect } from 'react';
import { NavLink } from 'react-router-dom';
import axios from 'axios';
import Profile from '../assets/p.png';

function AllContacts() {
  const [contacts, setContacts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchContacts = async () => {
    try {
      const response = await axios.get('http://localhost:5000/contacts');
      setContacts(response.data);
      setLoading(false);
    } catch (err) {
      setError(err);
      setLoading(false);
      console.error('Error fetching contacts:', err);
    }
  };

  useEffect(() => {
    fetchContacts();
  }, []);

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
    return <div>Error loading contacts: {error.message}</div>;
  }

  return (
    <div>
      <NavLink
        to={'add'}
        className="fixed bottom-5 right-5 rounded-2xl bg-blue-400 p-3 hover:bg-blue-700"
        aria-label="Add contact"
      >
        add
      </NavLink>
      {contacts.length > 0 ? (
        <div className="space-y-2 m-4 ">
          {contacts.map((contact) => (
            <div key={contact._id} className="flex justify-between rounded-xl hover:shadow-2xl p-2">
              <div className="flex space-x-3">
                <div className="flex justify-center items-center">
                  <div className="w-10 h-10 rounded-full overflow-hidden ">
                    <img src={Profile} alt="Contact Profile" className="w-full h-full object-cover" />
                  </div>
                </div>
                <div>
                  <div className="text-xl ">{contact.name}</div>
                  <div className="text-sm text-gray-500">{contact.phone}</div>
                </div>
              </div>
              <div className="flex space-x-3 items-center">
                <NavLink
                  to={`view/${contact._id}`}
                  className=" cursor-pointer rounded w-12 text-center hover:shadow-2xl bg-slate-300"
                  aria-label="View contact"
                >
                  View
                </NavLink>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>No contacts found.</p>
      )}
    </div>
  );
}

export default AllContacts;