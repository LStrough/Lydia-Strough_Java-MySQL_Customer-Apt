package model;

/**
 * This class is for first level divisions.
 *
 * @author Lydia Strough
 */
public class Division {
    /**
     * first level division ID.
     * */
    private int divisionId;
    /**
     *  country ID.
     */
    private int countryId;
    /**
     * first level division name.
     * */
    private String divisionName;
    /**
     * country name.
     */
    private String countryName;

    /**
     * This is the first level divisions constructor.
     *
     * @param divisionId first level division ID
     * @param countryId country ID
     * @param divisionName first level division name
     * @param countryName country name
     * */
    public Division(int divisionId, int countryId, String divisionName, String countryName) {
        this.divisionId = divisionId;
        this.countryId = countryId;
        this.divisionName = divisionName;
        this.countryName = countryName;
    }

    /**
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId first level division ID to set
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
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * @param divisionName first level division name to set
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName country name to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * This is the First Level Divisions toString method.
     *
     * This method provides default syntax for first level division information (converts hashcode to string, etc.).
     * */
    @Override
    public String toString() {
        return (divisionName);
    }
}
