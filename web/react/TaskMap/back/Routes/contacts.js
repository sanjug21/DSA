const express = require('express');
const router = express.Router();
const Contact = require('../Models/Contact');
const { validateContacts } = require('../middleware');

router.get('/contacts', async (req, res) => {
    try {
        const contacts = await Contact.find().sort({ name: 1 });
        res.json(contacts);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

router.post('/contacts/add', validateContacts, async (req, res) => {
    try {
        const { name, email, phone, secondaryPhone, secondaryEmail, dob } = req.body;
        const existingContact = await Contact.findOne({ name });

        if (existingContact) {
            return res.status(409).json({ message: 'Contact with this name already exists' });
        }
        const newContact = new Contact({
            name,
            email,
            phone,
            secondaryPhone,
            secondaryEmail,
            dob,
        });

        const savedContact = await newContact.save();
        res.status(201).json({ message: 'Contact saved successfully', contact: savedContact });
    } catch (err) {
        console.error('Error saving contact:', err);
        res.status(500).json({ error: 'Failed to save contact', message: err.message });
    }
});

router.get('/contacts/:id', async (req, res) => {
    try {
        const contact = await Contact.findById(req.params.id);
        if (!contact) {
            return res.status(404).json({ message: 'Contact not found' });
        }
        res.json(contact);
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

router.put('/contacts/:id', validateContacts, async (req, res) => {
    try {
        const updatedContact = await Contact.findByIdAndUpdate(req.params.id, req.body, { new: true });
        
        if (!updatedContact) {
            return res.status(404).json({ message: 'Contact not found' });
        }
        res.json({ message: 'Contact updated successfully', contact: updatedContact });
    } catch (err) {
        res.status(400).json({ message: err.message });
    }
});

router.delete('/contacts/:id', async (req, res) => {
    try {
        const deletedContact = await Contact.findByIdAndDelete(req.params.id);

        if (!deletedContact) {
            return res.status(404).json({ message: 'Contact not found' });
        }

        res.json({ message: 'Contact deleted successfully' });
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
});

module.exports = router;