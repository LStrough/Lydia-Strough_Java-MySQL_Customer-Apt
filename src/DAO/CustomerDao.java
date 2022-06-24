package DAO;

import javafx.collections.ObservableList;
import model.Customer;

/**
 * This is the "Customer DAO" class.
 * This class acts as an Interface for the "Customer DAO Implementation" class.
 *
 * @author Lydia Strough
 */
public interface CustomerDao {
    /**
     * This is the "get all Customers" method.
     * This method accesses the database and returns all customers. Each customer is then added to an observable list, "allCustomers".
     *
     * @return allCustomers list
     */
    public ObservableList<Customer> getAllCustomers();

    /**
     * This is the "get Customer" method.
     * This method searches the database for a specific customer based on its unique customer ID.
     *
     * @param customerId the customer in questions' customer ID
     * @return the customer in questions' information
     * @return no result (null), if no customer with the specific ID exists
     */
    public Customer getCustomer(int customerId);

    /**
     * This is the "get customers by country" method.
     * This method accesses the database and filters a list of customers based on their related country ID.
     *
     * @param countryId the customers' country ID is question
     * @return a "customers by country" list
     */
    public ObservableList<Customer> getCustomersByCountry(int countryId);

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
    public int updateCustomer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId);

    /**
     * This is the "delete customer" method.
     * <p>This method accesses the database and deletes a customer with a specific customer ID and customer name.
     * If the customer was successfully (or unsuccessfully) deleted, an alert message populates with the result of the query. </p>
     *
     * @param customerId the customer in questions' unique customer ID
     * @param customerName the customer in questions' customer name
     * @return the number of affected database rows
     */
    public int deleteCustomer(int customerId, String customerName);

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
    public int addCustomer(String customerName, String address, String postalCode, String phone, int divisionId);

    /**
     * This is the "look up customer" method.
     * This method searches the allCustomers list for a specific customer.
     *
     * @param customerId the desired customers' unique customer ID
     * @return the customer associated with the customer ID
     * @return no customer, if there is no match
     */
    public Customer lookUpCustomer(int customerId);

    /**
     * This is the "look up customer" method.
     * This method searches the allCustomers list for a filtered list of customers that are associated with a specific customer name.
     *
     * @param customerName the customer name string in question
     * @return the allCustomers list, if there are no matching customers
     * @return the filteredCustomers list of customers associated with the customer name string
     */
    public ObservableList<Customer>lookUpCustomer(String customerName);
}
