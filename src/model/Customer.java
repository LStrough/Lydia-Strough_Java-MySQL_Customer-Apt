package model;

/**
 * This class is for Customers.
 *
 * @author Lydia Strough
 */
public class Customer {
    /**
     * customer ID.
     * */
    private int customerId;
    /**
     * first level division ID.
     */
    private int divisionId;
    /**
     * country ID.
     */
    private int countryId;
    /**
     * customer name.
     * */
    private String customerName;
    /**
     * customer address.
     */
    private String address;
    /**
     *  customer postal code.
     */
    private String postalCode;
    /**
     *  customer phone number.
     */
    private String phone;
    /**
     * customer country name.
     */
    private String countryName;
    /**
     * customer first level division name.
     */
    private String divisionName;

    /**
     * This is the Month &amp; Type Report constructor.
     *
     * @param customerId customer ID
     * @param divisionId first level division ID
     * @param countryId country ID
     * @param customerName customer name
     * @param address customer address
     * @param postalCode customer postal code
     * @param phone customer phone number
     * @param countryName customer country name
     * @param divisionName customer first level division name
     * */
    public Customer(int customerId, int divisionId, int countryId, String customerName, String address, String postalCode,
                    String phone, String countryName, String divisionName) {
        this.customerId = customerId;
        this.divisionId = divisionId;
        this.countryId = countryId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.countryName = countryName;
        this.divisionName = divisionName;
    }

    /**
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId customer ID to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId customer first level division ID to set
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId country ID to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName customer name to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address customer address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode customer postal code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone customer phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName customer country name to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * @param divisionName customer first level division name to set
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * This is the Customers toString method.
     *
     * This method provides default syntax for customer information (converts hashcode to string, etc.).
     * */
    @Override
    public String toString() {
        return ("[" + Integer.toString(customerId) + "] " + customerName);
    }
}
