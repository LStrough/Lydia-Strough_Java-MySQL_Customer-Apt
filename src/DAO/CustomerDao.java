package DAO;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerDao {
    public ObservableList<Customer> getAllCustomers();
    public Customer getCustomer(int customerId);
    public int updateCustomerName(int customerId, String currentName, String newName);
    public int updateCustomerPhone(int customerId, String currentPhone, String newPhone);
    public int updateCustomerAddress(int customerId, String currentAddress, String newAddress);
    public int updateCustomerPostalCode(int customerId, String currentPostalCode, String newPostalCode);
    public int updateCustomerDivision(int customerId, int currentDivisionId, int newDivisionId);
    public int deleteCustomer(int customerId, String customerName);
    public int addCustomer(String customerName, String address, String postalCode, String phone, int divisionId);
    public Customer lookUpCustomer(int customerId);
    public ObservableList<Customer>lookUpCustomer(String customerName);
}
