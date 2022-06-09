package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class DivisionDaoImpl implements DivisionDao{
    ObservableList<Division> divisions = FXCollections.observableArrayList();

    public ObservableList<Division> getAllDivisions(){
        try{
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int divisionId = result.getInt("Division_ID");
                String divisionName = result.getString("Division");
                int countryId = result.getInt("Country_ID");
                Division division = new Division(divisionId, divisionName, countryId);
                divisions.add(division);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return divisions;
    }

    public Division getDivision(int divisionId){
        return divisions.get(divisionId);
    }

    public void updateDivision(int index, Division newDivision){
        divisions.set(index, newDivision);
    }

    public boolean deleteDivision(Division selectedDivision){
        return divisions.remove(selectedDivision);
    }

    public void addDivision(Division division){
        divisions.add(division);
    }
}
