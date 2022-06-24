package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

/**
 * This is the "Country DAO Implementation" class.
 * This class Implements the "Country DAO" class' method definitions.
 *
 * @author Lydia Strough
 */
public class CountryDaoImpl implements CountryDao{
    /**
     * This is the "all countries" list.
     */
    ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * This is the "get all Countries" method.
     * This method accesses the database and returns all countries. Each country is then added to an observable list, "allCountries".
     *
     * @return allCountries list
     */
    @Override
    public ObservableList<Country> getAllCountries() {
         try{
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int countryId = result.getInt("Country_ID");
                String countryName = result.getString("Country");
                Country country = new Country(countryId, countryName);
                allCountries.add(country);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return allCountries;
    }

    /**
     * This is the "get country" method.
     * This method searches the database for a specific country based on its unique country ID.
     *
     * @param countryId the desired countries' unique country ID
     * @return the specific countries' information
     */
    @Override
    public Country getCountry(int countryId) {
         try{
            String sql = "SELECT * FROM countries WHERE Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryId);

            ResultSet result = ps.executeQuery();
            Country countryResult = null;
            if(result.next()) {
                countryId = result.getInt("Country_ID");
                String countryName = result.getString("Country");
                countryResult = new Country(countryId, countryName);
            }
            return countryResult;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

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
    @Override
    public int updateCountry(int countryId, String currentCountryName, String newCountryName) {
        int rowsAffected = 0;
          try {
            String sql = "UPDATE countries SET Country=? WHERE Country=? AND Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newCountryName);
            ps.setString(2, currentCountryName);
            ps.setInt(3, countryId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(currentCountryName + " country name UPDATE was successful!");
                System.out.println("New country name: " + newCountryName);
            } else {
                System.out.println(currentCountryName + " country name UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

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
    @Override
    public int deleteCountry(int countryId, String countryName) {
        int rowsAffected = 0;
        JDBC.openConnection();
        DivisionDao divisionDao = new DivisionDaoImpl();
        try{
            if(divisionDao.getDivisionsByCountry(countryId).isEmpty()) {
                try {
                    String sql = "DELETE FROM countries WHERE Country_ID=? AND Country=?";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, countryId);
                    ps.setString(2, countryName);
                    rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Country: " + countryId + " " + countryName + " was successfully deleted!");
                    } else {
                        System.out.println("Country DELETE failed!");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            if(!divisionDao.getDivisionsByCountry(countryId).isEmpty()){
                System.out.println(countryName + " failed to DELETE!");
                System.out.println(countryName + " has associated divisions. DELETE remaining divisions in order to continue.");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    /**
     * This is the "add country" method.
     * This method accesses the database and adds a country with the desired country name.
     *
     * @param countryName the desired country name
     * @return the number of affected database rows
     */
    @Override
    public int addCountry(String countryName) {
        int rowsAffected = 0;
         try {
            String sql = "INSERT INTO countries (Country) VALUES(?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, countryName);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Country INSERT was successful!");
            } else {
                System.out.println("Country INSERT failed!");
            }
        } catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
         }
        return rowsAffected;
    }
}
