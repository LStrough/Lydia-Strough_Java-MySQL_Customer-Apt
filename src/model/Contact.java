package model;

/**
 * This class is for Customer Contacts.
 *
 * @author Lydia Strough
 */
public class Contact {
    /**
     * customer contact ID.
     * */
    private int contactId;
    /**
     * customer contact name.
     * */
    private String contactName;

    /**
     * This is the Customer contact constructor.
     *
     * @param contactId customer contact ID
     * @param contactName customer contact name
     * */
    public Contact(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the customer contact ID to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the customer contact name to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * This is the Customer Contact toString method.
     *
     * This method provides default syntax for customer contact information (converts hashcode to string, etc.).
     * */
    @Override
    public String toString() {
        return ("[" + Integer.toString(contactId) + "] " + contactName);
    }
}
