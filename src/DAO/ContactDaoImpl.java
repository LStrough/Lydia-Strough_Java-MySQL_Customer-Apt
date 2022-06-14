package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class ContactDaoImpl implements ContactDao{
    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @Override
    public ObservableList<Contact> getAllContacts() {
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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return contacts;
    }

    @Override
    public Contact getContact(int contactId) {
          try{
            String sql = "SELECT * FROM contacts WHERE Contact_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contactId);

            ResultSet result = ps.executeQuery();
            Contact contactResult = null;
            if(result.next()) {
                contactId = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");
                contactResult = new Contact(contactId, contactName);
            }
            return contactResult;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int updateContact(int contactId, String currentContactName, String newContactName) {
        int rowsAffected = 0;
        try {
            String sql = "UPDATE contacts SET Contact_Name=? WHERE Contact_Name=? AND Contact_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newContactName);
            ps.setString(2, currentContactName);
            ps.setInt(3, contactId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(currentContactName + " name UPDATE was successful!");
                System.out.println("New username: " + newContactName);
            } else {
                System.out.println(currentContactName + " name UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int deleteContact(int contactId) {
        int rowsAffected = 0;
        try {
            String sql = "DELETE FROM contacts WHERE Contact_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contactId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Contact [" + contactId + "] was successfully deleted!");
            } else {
                System.out.println("Contact DELETE failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int addContact(String contactName) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO contacts (Contact_Name) VALUES(?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, contactName);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Contact INSERT was successful!");
            } else {
                System.out.println("Contact INSERT failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }
}
