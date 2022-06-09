package DAO;

import javafx.collections.ObservableList;
import model.Country;

interface CountryDao {
    public ObservableList<Country> getAllCountries();
    public Country getCountry(int countryId);
    public void updateCountry(int index, Country newCountry);
    public boolean deleteCountry(Country selectedCountry);
    public void addCountry(Country country);
}
