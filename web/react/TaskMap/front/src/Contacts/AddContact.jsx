import axios from 'axios';
import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import { FaArrowLeft } from 'react-icons/fa';

function AddContact() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [secondaryPhone, setSecondaryPhone] = useState('');
  const [secondaryEmail, setSecondaryEmail] = useState('');
  const [dob, setDob] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState(null);

  const API = 'http://localhost:5000';

  const submit = async (e) => {
    e.preventDefault();
    if (name && phone) {
      setLoading(true);
      setError(null);
      setSuccessMessage(null);
      try {
        const response = await axios.post(`${API}/contacts/add`, {
          name,
          email,
          phone,
          secondaryPhone,
          secondaryEmail,
          dob,
        });
        if (response.data.message === 'Contact with this name already exists') {
          setError('Contact with this name already exists');
        } else if (response.status === 201) {
          setSuccessMessage('Contact added successfully!');
          setName('');
          setEmail('');
          setPhone('');
          setSecondaryPhone('');
          setSecondaryEmail('');
          setDob('');
        } else {
          setError('Failed to add contact.');
          console.log('Contact addition failed:', response.data);
        }
      } catch (error) {
        setError('An unexpected error occurred.');
        console.error('Error adding contact:', error);
      } finally {
        setLoading(false);
      }
    } else {
      setError('Name and phone are required.');
    }
  };

  return (
    <div className="p-4   overflow-auto max-h-[calc(100vh-3rem)] ">
      <NavLink to={'/contacts'} className="flex items-center space-x-2 mb-6 text-blue-600 hover:text-blue-800">
        <FaArrowLeft size={20} />
        <span>Back to Contacts</span>
      </NavLink>

      <div className="max-w-md mx-auto p-6 bg-white rounded-lg shadow-xl hover:shadow-2xl">
        <h2 className="text-2xl font-semibold text-gray-800 mb-6">Add Contact</h2>
        <form onSubmit={submit}>
          <div className="mb-4">
            <label htmlFor="name" className="block text-sm font-medium text-gray-700">Name:</label>
            <input
              type="text"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              className="mt-1 p-3 w-full border rounded-md focus:ring-blue-500 focus:border-blue-500"
              placeholder="Enter name"
            />
          </div>

          <div className="mb-4">
            <label htmlFor="phone" className="block text-sm font-medium text-gray-700">Phone:</label>
            <input
              type="tel"
              id="phone"
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
              className="mt-1 p-3 w-full border rounded-md focus:ring-blue-500 focus:border-blue-500"
              placeholder="Enter phone number"
            />
          </div>

          <div className="mb-4">
            <label htmlFor="secondaryPhone" className="block text-sm font-medium text-gray-700">Secondary Phone:</label>
            <input
              type="tel"
              id="secondaryPhone"
              value={secondaryPhone}
              onChange={(e) => setSecondaryPhone(e.target.value)}
              className="mt-1 p-3 w-full border rounded-md focus:ring-blue-500 focus:border-blue-500"
              placeholder="Enter secondary phone number"
            />
          </div>

          <div className="mb-4">
            <label htmlFor="email" className="block text-sm font-medium text-gray-700">Email:</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="mt-1 p-3 w-full border rounded-md focus:ring-blue-500 focus:border-blue-500"
              placeholder="Enter email address"
            />
          </div>

          <div className="mb-4">
            <label htmlFor="secondaryEmail" className="block text-sm font-medium text-gray-700">Secondary Email:</label>
            <input
              type="email"
              id="secondaryEmail"
              value={secondaryEmail}
              onChange={(e) => setSecondaryEmail(e.target.value)}
              className="mt-1 p-3 w-full border rounded-md focus:ring-blue-500 focus:border-blue-500"
              placeholder="Enter secondary email address"
            />
          </div>

          <div className="mb-6">
            <label htmlFor="dob" className="block text-sm font-medium text-gray-700">Date of Birth:</label>
            <input
              type="date"
              id="dob"
              value={dob}
              onChange={(e) => setDob(e.target.value)}
              className="mt-1 p-3 w-full border rounded-md focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <button
            type="submit"
            className={`w-full py-3 px-6 rounded-md text-white font-semibold ${
              loading ? 'bg-gray-400 cursor-not-allowed' : 'bg-blue-600 hover:bg-blue-700'
            }`}
            disabled={loading}
          >
            {loading ? 'Adding...' : 'Add Contact'}
          </button>

          {error && <p className="mt-4 text-red-600">{error}</p>}
          {successMessage && <p className="mt-4 text-green-600">{successMessage}</p>}
        </form>
      </div>
    </div>
  );
}

export default AddContact;