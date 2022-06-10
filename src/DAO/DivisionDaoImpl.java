package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

public class DivisionDaoImpl implements DivisionDao{
    ObservableList<Division> divisions = FXCollections.observableArrayList();

    @Override
    public ObservableList<Division> getAllDivisions() {
        /*
         try{
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int divisionId = result.getInt("Division_ID");
                String divisionName = result.getString("Division");
                String password = result.getString("Password");
                User user = new User(userId, userName, password);
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return users;
         */
        return divisions;
    }

    @Override
    public Division getDivision(int divisionId) {
        return null;
    }

    @Override
    public Division getCountryDivisions(int countryId) {
        return null;
    }

    @Override
    public void updateDivision(int index, Division newDivision) {

    }

    @Override
    public boolean deleteUser(Division selectedDivision) {
        return false;
    }

    @Override
    public void addUser(Division division) {

    }
}
