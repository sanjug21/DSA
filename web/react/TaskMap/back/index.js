
const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const notesRoutes = require('./Routes/note');
const contactsRoutes = require('./Routes/contacts');
const app = express();
const env = require('dotenv');

env.config();

app.use(cors());
app.use(express.json());

mongoose.connect(process.env.MONGO_URL)
  .then(() => {
    console.log('Database connected successfully!');
  })
  .catch((error) => {
    console.error('Error connecting to database:', error);
  });

app.use(notesRoutes);
app.use(contactsRoutes);

const PORT = process.env.PORT || 5000; 

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});