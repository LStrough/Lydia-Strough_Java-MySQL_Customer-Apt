package DAO;

import javafx.collections.ObservableList;
import model.Country;

public interface CountryDao {
    public ObservableList<Country> getAllCountries();

    public Country getCountry(int countryId);

    public int updateCountry(int countryId, String currentCountryName, String newCountryName);

    public int deleteCountry(int countryId, String countryName);

    public int addCountry(String countryName);
}