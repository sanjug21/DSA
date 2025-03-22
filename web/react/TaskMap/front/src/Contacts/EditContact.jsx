import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, NavLink } from 'react-router-dom';
import axios from 'axios';

function EditContact() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [contact, setContact] = useState({
    name: '',
    phone: '',
    secondaryPhone: '',
    email: '',
    secondaryEmail: '',
    dob: '',
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchContact = async () => {
      try {
        const response = await axios.get(`http://localhost:5000/contacts/${id}`);
        setContact(response.data);
        setLoading(false);
      } catch (err) {
        setError(err);
        setLoading(false);
      }
    };

    fetchContact();
  }, [id]);

  const updateContactField = (e) => {
    setContact({ ...contact, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      console.log(contact);
      
      const { _id, __v, ...contactData } = contact;
        await axios.put(`http://localhost:5000/contacts/${id}`, contactData);
        navigate(`/contacts/view/${id}`);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
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

  return (
    <div className="p-5">
      <h2 className="text-2xl font-semibold mb-4">Edit Contact</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label htmlFor="name" className="block text-sm font-medium text-gray-700">Name</label>
          <input type="text" name="name" id="name" value={contact.name} onChange={updateContactField} className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 p-2 focus:shadow-2xl" aria-label="Contact Name" />
        </div>
        <div>
          <label htmlFor="phone" className="block text-sm font-medium text-gray-700">Phone</label>
          <input type="text" name="phone" id="phone" value={contact.phone} onChange={updateContactField} className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 p-2 focus:shadow-2xl" aria-label="Phone Number" />
        </div>
        <div>
          <label htmlFor="secondaryPhone" className="block text-sm font-medium text-gray-700">Secondary Phone</label>
          <input type="text" name="secondaryPhone" id="secondaryPhone" value={contact.secondaryPhone} onChange={updateContactField} className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 p-2 focus:shadow-2xl" aria-label="Secondary Phone Number" />
        </div>
        <div>
          <label htmlFor="email" className="block text-sm font-medium text-gray-700">Email</label>
          <input type="email" name="email" id="email" value={contact.email} onChange={updateContactField} className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 p-2 focus:shadow-2xl" aria-label="Email Address" />
        </div>
        <div>
          <label htmlFor="secondaryEmail" className="block text-sm font-medium text-gray-700">Secondary Email</label>
          <input type="email" name="secondaryEmail" id="secondaryEmail" value={contact.secondaryEmail} onChange={updateContactField} className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 p-2 focus:shadow-2xl" aria-label="Secondary Email Address" />
        </div>
        <div>
          <label htmlFor="dob" className="block text-sm font-medium text-gray-700">Date of Birth</label>
          <input type="date" name="dob" id="dob" value={contact.dob ? contact.dob.substring(0, 10) : ''} onChange={updateContactField} className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 p-2 focus:shadow-2xl" aria-label="Date of Birth" />
        </div>
        <div className="flex justify-end space-x-4">
          <NavLink to={`/contacts/view/${id}`} className="bg-gray-300 hover:bg-gray-400 text-black font-bold py-2 px-4 rounded">Cancel</NavLink>
          <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" disabled={loading}>
            {loading ? 'Updating...' : 'Update'}
          </button>
        </div>
        {error && <div className="text-red-500 mt-2">Error: {error.message}</div>}
      </form>
    </div>
  );
}

export default EditContact;