package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

/**
 * This is the "Division DAO Implementation" class.
 * This class Implements the "Division DAO" class' method definitions.
 *
 * @author Lydia Strough
 */
public class DivisionDaoImpl implements DivisionDao{
    /**
     * This is the Division "all divisions" list.
     * */
    ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    /**
     * This is the Division "divisions by country" list.
     * This is a filtered list of "divisions" - each object in this list comes from the same country.
     * */
    ObservableList<Division> divisionsByCountry = FXCollections.observableArrayList();

    /**
     * This is the "get all Divisions" method.
     * This method accesses the database and returns all divisions. Each division is then added to an observable list, "allDivisions".
     *
     * @return allDivisions list
     */
    @Override
    public ObservableList<Division> getAllDivisions() {
         try{
            String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID " +
                    "= countries.Country_ID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int divisionId = result.getInt("Division_ID");
                int countryId = result.getInt("Country_ID");
                String divisionName = result.getString("Division");
                String countryName = result.getString("Country");
                Division division = new Division(divisionId, countryId, divisionName, countryName);
                allDivisions.add(division);
            }
        }catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
         }
        return allDivisions;
    }

    /**
     * This is the "get division" method.
     * This method searches the database for a specific division based on its unique division ID.
     *
     * @param divisionId the division ID in question
     * @return the specific divisions' information
     * @return no result (null), if no division with the specific ID exists
     */
    @Override
    public Division getDivision(int divisionId) {
        try{
            String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID " +
                    "= countries.Country_ID AND Division_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, divisionId);

            ResultSet result = ps.executeQuery();
            Division divisionResult = null;
            if(result.next()) {
                divisionId = result.getInt("Division_ID");
                int countryId = result.getInt("Country_ID");
                String divisionName = result.getString("Division");
                String countryName = result.getString("Country");
                divisionResult = new Division(divisionId, countryId, divisionName, countryName);
            }
            return divisionResult;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * This is the "get divisions by country" method.
     * This method accesses the database and filters the list of divisions based on their related country ID.
     *
     * @param countryId the divisions' country ID
     * @return the "divisionsByCountry" list
     */
    @Override
    public ObservableList<Division> getDivisionsByCountry(int countryId) {
         try{
            String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID " +
                    "= countries.Country_ID AND first_level_divisions.Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryId);

            ResultSet result = ps.executeQuery();
            while(result.next()) {
                int divisionId = result.getInt("Division_ID");
                countryId = result.getInt("Country_ID");
                String divisionName = result.getString("Division");
                String countryName = result.getString("Country");
                Division division = new Division(divisionId, countryId, divisionName, countryName);
                divisionsByCountry.add(division);
                 }
        } catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
         }
        return divisionsByCountry;
    }

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
    @Override
    public int updateDivisionName(String currentDivisionName, int countryId, String newDivisionName) {
        int rowsAffected = 0;
            try {
            String sql = "UPDATE first_level_divisions SET Division=? WHERE Division=? AND Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newDivisionName);
            ps.setString(2, currentDivisionName);
            ps.setInt(3, countryId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(currentDivisionName + " division UPDATE was successful!");
                System.out.println("New division name: " + newDivisionName);
            } else {
                System.out.println(currentDivisionName + " division name UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

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
    @Override
    public int updateDivisionCountry(String divisionName, int currentCountryId, int newCountryId) {
        int rowsAffected = 0;
          try {
            String sql = "UPDATE first_level_divisions SET Country_ID=? WHERE Division=? AND Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, newCountryId);
            ps.setString(2, divisionName);
            ps.setInt(3, currentCountryId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(divisionName + " country UPDATE was successful!");
            } else {
                System.out.println(divisionName + " country UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    /**
     * This is the "delete division" method.
     * This method accesses the database and deletes a division with a specific division name and division ID.
     *
     * @param divisionId the division in questions' division ID
     * @param divisionName the division in questions' division name
     * @return the number of affected database rows
     */
    @Override
    public int deleteDivision(int divisionId, String divisionName) {
        int rowsAffected = 0;
          try {
            String sql = "DELETE FROM first_level_divisions WHERE Division_ID=? AND Division=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, divisionId);
            ps.setString(2, divisionName);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Division: " + divisionId + " " + divisionName + " was successfully deleted!");
            } else {
                System.out.println("Division DELETE failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    /**
     * This is the "add division" method.
     * This method accesses the database and adds a division with the desired division name and country ID.
     *
     * @param divisionName the desired divisions' division name
     * @param countryId the desired divisions' country ID
     * @return the number of affected database rows
     */
    @Override
    public int addDivision(String divisionName, int countryId) {
        int rowsAffected = 0;
           try {
            String sql = "INSERT INTO first_level_divisions (Division, Country_ID) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, divisionName);
            ps.setInt(2, countryId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Division INSERT was successful!");
            } else {
                System.out.println("Division INSERT failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }
}
