package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class CountyDaoImpl implements CountryDao{
    ObservableList<Country> countries = FXCollections.observableArrayList();

    public ObservableList<Country> getAllCountries(){
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
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return countries;
    }

    public Country getCountry(int countryId){
        return countries.get(countryId);
    }

    public void updateCountry(int index, Country newCountry){
        countries.set(index, newCountry);
    }

    public boolean deleteCountry(Country selectedCountry){
        return countries.remove(selectedCountry);
    }

    public void addCountry(Country country){
        countries.add(country);
    }
}
