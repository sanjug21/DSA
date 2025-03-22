const express = require('express');
const Note = require('../Models/Note');
const router = express.Router();
const { validateNote } = require('../middleware'); 

router.get('/notes', async (req, res) => {
    try {
      const notes = await Note.find().sort({ createdAt: -1 });
      res.json(notes);
    } catch (error) {
      res.status(500).json({ error: 'Failed to fetch notes' });
    }
  });

router.post('/notes/add', validateNote, async (req, res) => { 
 
  try {
     let { title, description } = req.body;
     if (title === '') {
      title = 'Untitled';
    }   
    const newNote = new Note({ title, description });
    const savedNote = await newNote.save();
    res.json(savedNote);
  } catch (err) {
    console.log("Error saving note:", err.message);
    // res.status(500).json({ error: 'Failed to save note' });
  }
});
router.put('/notes/:id', validateNote, async (req, res) => {
  try {
      const { title, description } = req.body;
      const updatedNote = await Note.findByIdAndUpdate(
          req.params.id,
          { title, description },
          
      );
      if (!updatedNote) {
          return res.status(404).json({ error: 'Note not found' });
      }
      res.status(200).json(updatedNote);
  } catch (error) {
      console.error("Error updating note:", error);
      res.status(500).json({ error: 'Failed to update note' });
  }
});

router.delete('/notes/:id', async (req, res) => {
  try {
      const deletedNote = await Note.findByIdAndDelete(req.params.id);
      if (!deletedNote) {
          return res.status(404).json({ error: 'Note not found' });
      }
      res.status(200).json({ message: 'Note deleted successfully' });
  } catch (error) {
      console.error("Error deleting note:", error);
      res.status(500).json({ error: 'Failed to delete note' });
  }
});

module.exports = router;