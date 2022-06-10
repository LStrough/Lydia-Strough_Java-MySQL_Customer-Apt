package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class DivisionDaoImpl implements DivisionDao{
    ObservableList<Division> divisions = FXCollections.observableArrayList();
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
                divisions.add(division);
            }
        }catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
         }
        return divisions;
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
    public void updateDivision(int index, Division newDivision) {
        //mySQL update database!
    }

    @Override
    public boolean deleteUser(Division selectedDivision) {
        //mySQL delete from database!
        return false;
    }

    @Override
    public void addUser(Division division) {
        //mySQL add to database!
    }
}
