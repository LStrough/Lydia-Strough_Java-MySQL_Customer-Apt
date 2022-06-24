package DAO;

import javafx.collections.ObservableList;
import model.Division;

/**
 * This is the "Division DAO" class.
 * This class acts as an Interface for the "Division DAO Implementation" class
 *
 * @author Lydia Strough
 */
public interface DivisionDao {
    /**
     * This is the "get all Divisions" method.
     * This method accesses the database and returns all divisions. Each division is then added to an observable list "allDivisions".
     *
     * @return allDivisions list
     */
    public ObservableList<Division> getAllDivisions();

    /**
     * This is the "get division" method.
     * This method searches the database for a specific division based on its unique division ID.
     *
     * @param divisionId the division ID in question
     * @return the specific divisions' information
     */
    public Division getDivision(int divisionId);

    /**
     * This is the "get divisions by country" method.
     * This method accesses the database and filters the list of divisions based on their related country ID.
     *
     * @param countryId the divisions' country ID
     * @return the "divisionsByCountry" list
     */
    public ObservableList<Division> getDivisionsByCountry(int countryId);

    /**
     * This is the "update division name" method.
     * <p>This method searches the database for a specific division by their division name and country ID,
     * and then updates the divisions' division name.</p>
     *
     * @param currentDivisionName the division in questions' current division name
     * @param countryId the division in questions' country ID
     * @param newDivisionName the division in questions' desired new division name
     * @return the number of affected database rows
     */
    public int updateDivisionName(String currentDivisionName, int countryId, String newDivisionName);

    /**
     * This is the "update division country ID" method.
     * <p>This method searched the database for a specific division by their division name and country ID,
     * and then updates the divisions' country ID. </p>
     *
     * @param divisionName the division in questions' division name
     * @param currentCountryId the division in questions' current country ID
     * @param newCountryId the division in questions' desired new country ID
     * @return the number of affected database rows
     */
    public int updateDivisionCountry(String divisionName, int currentCountryId, int newCountryId);

    /**
     * This is the "delete division" method.
     * This method accesses the database and deletes a division with a specific division name and division ID.
     *
     * @param divisionId the division in questions' division ID
     * @param divisionName the division in questions' division name
     * @return the number of affected database rows
     */
    public int deleteDivision(int divisionId, String divisionName);

    /**
     * This is the "add division" method.
     * This method accesses the database and adds a division with the desired division name and country ID.
     *
     * @param divisionName the desired divisions' division name
     * @param countryId the desired divisions' country ID
     * @return the number of affected database rows
     */
    public int addDivision(String divisionName, int countryId);
}
