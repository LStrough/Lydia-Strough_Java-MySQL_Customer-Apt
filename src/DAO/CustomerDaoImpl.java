package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class CustomerDaoImpl implements CustomerDao{
    ObservableList<Customer> customers = FXCollections.observableArrayList();

    public ObservableList<Customer> getAllCustomers(){
        try{
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int customerId = result.getInt("Customer_ID");
                String customerName = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                int divisionId = result.getInt("Division_ID");
                Customer customer = new Customer(customerId, customerName, address, postalCode, phone, divisionId);
                customers.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return customers;
    }

    public Customer getCustomer(int customerId){
        return customers.get(customerId);
    }

    public void updateCustomer(int index, Customer newCustomer){
        customers.set(index, newCustomer);
    }

    public boolean deleteCustomer(Customer selectedCustomer){
        return customers.remove(selectedCustomer);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }
}
