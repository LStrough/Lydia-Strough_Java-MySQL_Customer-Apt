package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class UserCRUD {

    public static ObservableList<User> getAllUsers(){
        ObservableList<User> users = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM users";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int userId = result.getInt("User_ID");
                String userName = result.getString("User_Name");
                String password = result.getString("Password");
                User user = new User(userId, userName, password);
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return users;
    }

    public static void display(){
        System.out.println("CRUD");
    }

    public static void displayV2(){
        System.out.println("CRUD");
    }
}
