package DAO;

import javafx.collections.ObservableList;
import model.Contact;

/**
 * This is the "Contact DAO" class.
 * This class acts as an Interface for the "Contact DAO Implementation" class.
 *
 * @author Lydia Strough
 */
public interface ContactDao {
    /**
     * This is the "get All Contacts" method.
     * This method accesses the database and returns all contacts. Each contact is then added to an observable list, "allContacts".
     *
     * @return allContacts list
     */
    public ObservableList<Contact> getAllContacts();

    /**
     * This is the "get contact" method.
     * This method searches the database for a specific contact by their unique contact ID.
     *
     * @param contactId the contact in questions' contact ID
     * @return the specific contacts' information
     * @return no result (null), if no contact with the specific ID exists
     */
    public Contact getContact(int contactId);

    /**
     * This is the "update contact" method.
     * This method searches the database for a specific contact by their contact ID and contact name, and then updates the contact name.
     *
     * @param contactId the contact in questions' contact ID
     * @param currentContactName the contact in questions' contact name
     * @param newContactName the contact in questions' desired new contact name
     * @return the number of affected database rows
     */
    public int updateContact(int contactId, String currentContactName, String newContactName);

    /**
     * This is the "delete contact" method.
     * This method accesses the database and deletes a contact with the specified contact ID.
     *
     * @param contactId the contact in questions' unique contact ID
     * @return the number of affected database rows
     */
    public int deleteContact(int contactId);

    /**
     * This is the "add contact" method.
     * This method accesses the database and adds a new contact with the desired contact name.
     *
     * @param contactName the desired contact name
     * @return the number of affected database rows
     */
    public int addContact(String contactName);
}
