package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class UserDaoImpl implements UserDao{
    ObservableList<User> users = FXCollections.observableArrayList();

    public ObservableList<User> getAllUsers(){
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

    public User getUser(int userId) {
        return users.get(userId);
    }

    public void updateUser(int index, User newUser) {
        users.set(index, newUser);
    }

    public boolean deleteUser(User selectedUser) {
        return users.remove(selectedUser);
    }

    public void addUser(User user){
        users.add(user);
    }
}
