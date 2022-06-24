package model;

/**
 * This class is for Countries.
 *
 * @author Lydia Strough
 */
public class Country {
    /**
     * country ID.
     * */
    private int countryId;
    /**
     * country name.
     * */
    private String countryName;

    /**
     * This is the Country constructor.
     *
     * @param countryId country ID
     * @param countryName country name
     * */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * @return the countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the country ID to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the country name to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * This is the Country toString method.
     *
     * This method provides default syntax for country information (converts hashcode to string, etc.).
     * */
    @Override
    public String toString() {
        return (countryName);
    }
}
