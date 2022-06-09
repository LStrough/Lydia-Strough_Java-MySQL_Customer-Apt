package DAO;

import javafx.collections.ObservableList;
import model.Customer;

interface CustomerDao {
    public ObservableList<Customer> getAllCustomers();
    public Customer getCustomer(int customerId);
    public void updateCustomer(int index, Customer newCustomer);
    public boolean deleteCustomer(Customer selectedCustomer);
    public void addCustomer(Customer customer);
}
