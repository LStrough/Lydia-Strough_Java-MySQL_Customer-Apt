package DAO;

import javafx.collections.ObservableList;
import model.Contact;

public interface ContactDao {
    public ObservableList<Contact> getAllContacts();
    public Contact getContact(int contactId);
    public int updateContact(int contactId, String currentContactName, String newContactName);
    public int deleteContact(int contactId);
    public int addContact(String contactName);
}
