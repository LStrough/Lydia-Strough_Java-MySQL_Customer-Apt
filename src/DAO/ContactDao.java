package DAO;

import javafx.collections.ObservableList;
import model.Contact;

interface ContactDao {
    public ObservableList<Contact> getAllContacts();
    public Contact getContact(int contactId);
    public void updateContact(int index, Contact newContact);
    public boolean deleteContact(Contact selectedContact);
    public void addContact(Contact contact);
}
