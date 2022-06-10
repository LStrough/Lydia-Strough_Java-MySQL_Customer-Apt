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
    public void updateCountry(int index, Country newCountry) {
        //mySQL update database!
    }

    @Override
    public boolean deleteCountry(Country selectedCountry) {
        return false;
        //mySQL delete from database!
    }

    @Override
    public void addCountry(Country country) {
        //mySQL add to database!
    }
}
