package DAO;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerDao {
    public ObservableList<Customer> getAllCustomers();
    public Customer getCustomer(int customerId);
    public ObservableList<Customer> getCustomersByCountry(int countryId);
    public int updateCustomer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId);
    public int deleteCustomer(int customerId, String customerName);
    public int addCustomer(String customerName, String address, String postalCode, String phone, int divisionId);
    public Customer lookUpCustomer(int customerId);
    public ObservableList<Customer>lookUpCustomer(String customerName);
}
