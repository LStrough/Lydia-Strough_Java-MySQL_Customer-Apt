package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class DivisionDaoImpl implements DivisionDao{
    ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    ObservableList<Division> divisionsByCountry = FXCollections.observableArrayList();

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
