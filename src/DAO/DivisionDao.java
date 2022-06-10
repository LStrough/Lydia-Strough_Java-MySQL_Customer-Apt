package DAO;

import javafx.collections.ObservableList;
import model.Division;

public interface DivisionDao {
    public ObservableList<Division> getAllDivisions();
    public Division getDivision(int divisionId);
    public Division getCountryDivisions(int countryId);
    public void updateDivision(int index, Division newDivision);
    public boolean deleteUser(Division selectedDivision);
    public void addUser(Division division);
}
