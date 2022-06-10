package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public void updateCustomer(int index, Customer newCustomer) {
        //mySQL update database!
    }

    @Override
    public boolean deleteCustomer(Customer selectedCustomer) {
        return false;
        //mySQL delete from database!
    }

    @Override
    public void addCustomer(Customer customer) {
        //mySQL add to database!
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
