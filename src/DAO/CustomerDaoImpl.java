package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class CustomerDaoImpl implements CustomerDao{
    ObservableList<Customer> customers = FXCollections.observableArrayList();
    public boolean customerFound;

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
                customers.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return customers;
    }

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

    @Override
    public int updateCustomerName(int customerId, String currentName, String newName) {
       int rowsAffected = 0;
            try {
            String sql = "UPDATE customers SET Customer_Name=? WHERE Customer_Name=? AND Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newName);
            ps.setString(2, currentName);
            ps.setInt(3, customerId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(currentName + " name UPDATE was successful!");
                System.out.println("New name: " + newName);
            } else {
                System.out.println(currentName + " name UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int updateCustomerPhone(int customerId, String currentPhone, String newPhone) {
        int rowsAffected = 0;
            try {
            String sql = "UPDATE customers SET Phone=? WHERE Phone=? AND Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newPhone);
            ps.setString(2, currentPhone);
            ps.setInt(3, customerId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer " + customerId + " phone number UPDATE was successful!");
                System.out.println("New phone number: " + newPhone);
            } else {
                System.out.println("Customer " + customerId + " phone number UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int updateCustomerAddress(int customerId, String currentAddress, String newAddress) {
        int rowsAffected = 0;
            try {
            String sql = "UPDATE customers SET Address=? WHERE Address=? AND Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newAddress);
            ps.setString(2, currentAddress);
            ps.setInt(3, customerId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer " + customerId + " address UPDATE was successful!");
                System.out.println("New address: " + newAddress);
            } else {
                System.out.println("Customer " + customerId + " address UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int updateCustomerPostalCode(int customerId, String currentPostalCode, String newPostalCode) {
        int rowsAffected = 0;
            try {
            String sql = "UPDATE customers SET Postal_Code=? WHERE Postal_Code=? AND Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newPostalCode);
            ps.setString(2, currentPostalCode);
            ps.setInt(3, customerId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer " + customerId + " postal code UPDATE was successful!");
                System.out.println("New postal code: " + newPostalCode);
            } else {
                System.out.println("Customer " + customerId + " postal code UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int updateCustomerDivision(int customerId, int currentDivisionId, int newDivisionId) {
          int rowsAffected = 0;
            try {
            String sql = "UPDATE customers SET Division_ID=? WHERE Division_ID=? AND Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, newDivisionId);
            ps.setInt(2, currentDivisionId);
            ps.setInt(3, customerId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer " + customerId + " division UPDATE was successful!");
                System.out.println("New division: " + newDivisionId);
            } else {
                System.out.println("Customer " + customerId + " division UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

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
        }
        return rowsAffected;
    }

    @Override
    public int deleteCustomer(int customerId, String customerName) {     //check that customerAppointments .isEmpty()
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
                alert.setContentText("[" + customerId + "] " + customerName + " was successfully deleted.");
                alert.showAndWait();
            } else {
                System.out.println("Customer DELETE failed!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("[" + customerId + "] " + customerName + " failed to deleted.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

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

    @Override
    public Customer lookUpCustomer(int customerId) {
        customerFound = false;
        for(Customer customer : customers){
            if(customer.getCustomerId() == customerId){
                customerFound = true;
                return customer;
            }
        }
        return null;
    }

    @Override
    public ObservableList<Customer> lookUpCustomer(String customerName) {
        ObservableList<Customer> filteredCustomers = FXCollections.observableArrayList();
        customerFound = false;

        for(Customer customer : customers) {
            if(customer.getCustomerName().toLowerCase().contains(customerName.toLowerCase())){
                filteredCustomers.add(customer);
            }
        }
        if(filteredCustomers.isEmpty()) {
            return customers;
        }
        customerFound = true;
        return filteredCustomers;
    }

}
