package DAO;

import javafx.collections.ObservableList;
import model.Division;

public interface DivisionDao {
    public ObservableList<Division> getAllDivisions();
    public Division getDivision(int divisionId);
    public ObservableList<Division> getDivisionsByCountry(int countryId);
    public int updateDivisionName(String currentDivisionName, int countryId, String newDivisionName);
    public int updateDivisionCountry(String divisionName, int currentCountryId, int newCountryId);
    public int deleteDivision(int divisionId, String divisionName);
    public int addDivision(String divisionName, int countryId);
}
