package DAO;

import javafx.collections.ObservableList;
import model.Country;

/**
 * This is the "Country DAO" class.
 * This class acts as an Interface for the "Country DAO Implementation" class.
 *
 * @author Lydia Strough
 */
public interface CountryDao {
    /**
     * This is the "get all Countries" method.
     * This method accesses the database and returns all countries. Each country is then added to an observable list, "allCountries".
     *
     * @return allCountries list
     */
    public ObservableList<Country> getAllCountries();

    /**
     * This is the "get country" method.
     * This method searches the database for a specific country based on its unique country ID.
     *
     * @param countryId the desired countries' unique country ID
     * @return the specific countries' information
     */
    public Country getCountry(int countryId);

    /**
     * This is the "update country" method.
     * <p>This method searches the database for a specific country by their country name and country ID,
     * and then updates the countries' country name.</p>
     *
     * @param countryId the country in questions' unique country ID
     * @param currentCountryName the country in questions' current country name
     * @param newCountryName the country in questions' desired new country name
     * @return the number of affected database rows
     */
    public int updateCountry(int countryId, String currentCountryName, String newCountryName);

    /**
     * This is the "delete country" method.
     * <p>This method deletes a selected country if the countries' associated divisions list is empty. The method first
     * calls the "getDivisionsByCountry" method with a specific country ID. If the list returns not null, nothing happens.
     * Otherwise, if the filtered divisions list comes back empty, the method accesses the database and looks for a country with the
     * associated country ID and country name and deletes that specific country from the database.</p>
     *
     * @param countryId the country in questions' country ID
     * @param countryName the country in questions' country name
     * @return the number of affected database rows
     * @return an alert message, if the country failed to delete
     */
    public int deleteCountry(int countryId, String countryName);

    /**
     * This is the "add country" method.
     * This method accesses the database and adds a country with the desired country name.
     *
     * @param countryName the desired country name
     * @return the number of affected database rows
     */
    public int addCountry(String countryName);
}