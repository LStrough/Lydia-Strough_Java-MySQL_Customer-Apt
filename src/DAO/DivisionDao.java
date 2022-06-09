package DAO;

import javafx.collections.ObservableList;
import model.Division;

interface DivisionDao {
    public ObservableList<Division> getAllDivisions();
    public Division getDivision(int divisionId);
    public void updateDivision(int index, Division newDivision);
    public boolean deleteDivision(Division selectedDivision);
    public void addDivision(Division division);
}
