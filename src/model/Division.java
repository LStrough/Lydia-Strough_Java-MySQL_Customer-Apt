package model;

public class Division {
    private int divisionId, countryId;
    private String divisionName, countryName;

    public Division(int divisionId, int countryId, String divisionName, String countryName) {
        this.divisionId = divisionId;
        this.countryId = countryId;
        this.divisionName = divisionName;
        this.countryName = countryName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return (divisionName);
    }
}
