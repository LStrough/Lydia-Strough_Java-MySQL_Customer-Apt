package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class CountryDaoImpl implements CountryDao{
    ObservableList<Country> countries = FXCollections.observableArrayList();

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
                countries.add(country);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return countries;
    }

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
            else if(!divisionDao.getDivisionsByCountry(countryId).isEmpty()){
                System.out.println(countryName + " failed to DELETE!");
                System.out.println(countryName + " has associated divisions. DELETE remaining divisions in order to continue.");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        /*
         int rowsAffected = 0;
        JDBC.openConnection();
        DivisionDao divisionDao = new DivisionDaoImpl();
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
        else if(!divisionDao.getDivisionsByCountry(countryId).isEmpty()){
            System.out.println(countryName + " failed to DELETE!");
            System.out.println(countryName + " has associated divisions. DELETE remaining divisions in order to continue.");
        }
        else {
            System.out.println(countryName + " failed to DELETE!");
        }
        return rowsAffected;
         */
        return rowsAffected;
    }

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
