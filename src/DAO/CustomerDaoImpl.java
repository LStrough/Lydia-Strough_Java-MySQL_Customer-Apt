package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

/**
 * This is the "Customer DAO Implementation" class.
 * This class Implements the "Customer DAO" class' method definitions.
 *
 * @author Lydia Strough
 */
public class CustomerDaoImpl implements CustomerDao{
    /**
     * This is the "all customers" list.
     */
    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    /**
     * customer object that is used in the "look up customer" method(s).
     */
    public boolean customerFound;

    /**
     * This is the "get all Customers" method.
     * This method accesses the database and returns all customers. Each customer is then added to an observable list, "allCustomers".
     *
     * @return allCustomers list
     */
    @Override
    public ObservableList<Customer> getAllCustomers() {
        try{
            String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE " +
                    "customers.Division_ID = first_level_divisions.Division_ID AND " +
                    "first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int customerId = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");;
                int countryId = result.getInt("Country_ID");
                String customerName = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                String countryName = result.getString("Country");
                String divisionName = result.getString("Division");
                Customer customer = new Customer(customerId, divisionId, countryId, customerName, address, postalCode,
                        phone, countryName, divisionName);
                allCustomers.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return allCustomers;
    }

    /**
     * This is the "get Customer" method.
     * This method searches the database for a specific customer based on its unique customer ID.
     *
     * @param customerId the customer in questions' customer ID
     * @return the customer in questions' information
     * @return no result (null), if no customer with the specific ID exists
     */
    @Override
    public Customer getCustomer(int customerId) {
        try{
            String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE " +
                    "customers.Division_ID = first_level_divisions.Division_ID AND " +
                    "first_level_divisions.Country_ID = countries.Country_ID AND " +
                    "Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);

            ResultSet result = ps.executeQuery();
            Customer customerResult = null;
            if(result.next()) {
                customerId = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");;
                int countryId = result.getInt("Country_ID");
                String customerName = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                String countryName = result.getString("Country");
                String divisionName = result.getString("Division");
                customerResult = new Customer(customerId, divisionId, countryId, customerName, address, postalCode,
                        phone, countryName, divisionName);
            }
            return customerResult;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return null;
    }

    /**
     * This is the "get customers by country" method.
     * This method accesses the database and filters a list of customers based on their related country ID.
     *
     * @param countryId the customers' country ID is question
     * @return a "customers by country" list
     */
    @Override
    public ObservableList<Customer> getCustomersByCountry(int countryId) {
        ObservableList<Customer> customersByCountry = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE\n" +
                    "customers.Division_ID = first_level_divisions.Division_ID AND \n" +
                    "first_level_divisions.Country_ID = countries.Country_ID AND countries.Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryId);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int customerId = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");;
                countryId = result.getInt("Country_ID");
                String customerName = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                String countryName = result.getString("Country");
                String divisionName = result.getString("Division");
                Customer customer = new Customer(customerId, divisionId, countryId, customerName, address, postalCode,
                        phone, countryName, divisionName);
                customersByCountry.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return customersByCountry;
    }

    /**
     * This is the "update customer" method.
     * This method updates all values of a selected customer based on their unique customer ID.
     *
     * @param customerId the customer in questions' unique customer ID
     * @param customerName the customer in questions' desired customer name
     * @param address the customer in questions' desired customer address
     * @param postalCode the customer in questions' desired postal code
     * @param phone the customer in questions' desired phone number
     * @param divisionId the customer in questions' desired division ID
     * @return the number of affected database rows
     */
    @Override
    public int updateCustomer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) {
        int rowsAffected = 0;
        try{
            String sql = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            ps.setInt(6, customerId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer UPDATE was successful!");
            } else {
                System.out.println("Customer UPDATE Failed!");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return rowsAffected;
    }

    /**
     * This is the "delete customer" method.
     * <p>This method accesses the database and deletes a customer with a specific customer ID and customer name.
     * If the customer was successfully (or unsuccessfully) deleted, an alert message populates with the result of the query. </p>
     *
     * @param customerId the customer in questions' unique customer ID
     * @param customerName the customer in questions' customer name
     * @return the number of affected database rows
     */
    @Override
    public int deleteCustomer(int customerId, String customerName) {
        int rowsAffected = 0;
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID=? AND Customer_Name=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setString(2, customerName);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer: [" + customerId + "] " + customerName + " was successfully deleted!");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer DELETE");
                alert.setContentText("[" + customerId + "] " + customerName + " was successfully deleted.");
                alert.showAndWait();
            } else {
                System.out.println("Customer DELETE failed!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer DELETE");
                alert.setContentText("[" + customerId + "] " + customerName + " failed to deleted.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    /**
     * This is the "add customer" method.
     * <p>This method accesses the database and adds a customer to the system with the desired credentials (customer name,
     * customer address, customer postal code, customer phone number, and division ID). </p>
     *
     * @param customerName the desired customer name
     * @param address the desired customer address
     * @param postalCode the desired customer postal code
     * @param phone the desired customer phone number
     * @param divisionId the desired division ID
     * @return the number of affected database rows
     */
    @Override
    public int addCustomer(String customerName, String address, String postalCode, String phone, int divisionId) {
        int rowsAffected = 0;
            try {
                String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                        "VALUES(?,?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, customerName);
                ps.setString(2, address);
                ps.setString(3, postalCode);
                ps.setString(4, phone);
                ps.setInt(5, divisionId);
                rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Customer INSERT was successful!");
                }
                else {
                    System.out.println("Customer INSERT failed!");
                }
            }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        return rowsAffected;
    }

    /**
     * This is the "look up customer" method.
     * This method searched the allCustomers list for a specific customer.
     *
     * @param customerId the desired customers' unique customer ID
     * @return the customer associated with the customer ID
     * @return no customer, if there is no match
     */
    @Override
    public Customer lookUpCustomer(int customerId) {
        customerFound = false;
        for(Customer customer : allCustomers){
            if(customer.getCustomerId() == customerId){
                customerFound = true;
                return customer;
            }
        }
        return null;
    }

    /**
     * This is the "look up customer" method.
     * This method searches the allCustomers list for a filtered list of customers that are associated with a specific customer name.
     *
     * @param customerName the customer name string in question
     * @return the allCustomers list, if there are no matching customers
     * @return the filteredCustomers list of customers associated with the customer name string
     */
    @Override
    public ObservableList<Customer> lookUpCustomer(String customerName) {
        ObservableList<Customer> filteredCustomers = FXCollections.observableArrayList();
        customerFound = false;

        for(Customer customer : allCustomers) {
            if(customer.getCustomerName().toLowerCase().contains(customerName.toLowerCase())){
                filteredCustomers.add(customer);
            }
        }
        if(filteredCustomers.isEmpty()) {
            return allCustomers;
        }
        customerFound = true;
        return filteredCustomers;
    }

}
