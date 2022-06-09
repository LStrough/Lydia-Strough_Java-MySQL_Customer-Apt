package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class ContactDaoImpl implements ContactDao{
    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public ObservableList<Contact> getAllContacts(){
        try{
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int contactId = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");
                Contact contact = new Contact(contactId, contactName);
                contacts.add(contact);
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return contacts;
    }

    public Contact getContact(int contactId){
        return contacts.get(contactId);
    }

    public void updateContact(int index, Contact newContact){
        contacts.set(index, newContact);
    }

    public boolean deleteContact(Contact selectedContact){
        return contacts.remove(selectedContact);
    }

    public void addContact(Contact contact){
        contacts.add(contact);
    }
}
