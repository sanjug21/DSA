import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './layout';
import Notes from './Notes/Notes';
import Todos from './Todos/Todos';
import Events from './Events/Events';
import Contacts from './Contacts/Contacts';
import MySpace from './MySpace/MySpace';
import ContactDetails from './Contacts/ContactDetails';
import AddContact from './Contacts/AddContact';
import AllContacts from './Contacts/AllContacts';
import EditContact from './Contacts/EditContact';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<MySpace />} />
          <Route path="notes" element={<Notes />} />
          <Route path="todos" element={<Todos />} />
          <Route path="events" element={<Events />} />
          <Route path="contacts" element={<Contacts />}>
            <Route index element={<AllContacts />} />
            <Route path="view/:id" element={<ContactDetails />} />
            <Route path="add" element={<AddContact />} />
            <Route path="edit/:id" element={<EditContact />} />
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;