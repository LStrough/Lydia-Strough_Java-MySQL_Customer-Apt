package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class UserDaoImpl implements UserDao{
    ObservableList<User> allUsers = FXCollections.observableArrayList();

    @Override
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
                allUsers.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return allUsers;
    }

    @Override
    public User getUser(int userId) {
        try{
            String sql = "SELECT * FROM users WHERE User_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet result = ps.executeQuery();
            User userResult = null;
            if(result.next()) {
                userId = result.getInt("User_ID");
                String userName = result.getString("User_Name");
                String password = result.getString("Password");
                userResult = new User(userId, userName, password);
            }
            return userResult;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int updateUserPass(String userName, String newPassword, String currentPassword) {
        int rowsAffected = 0;
        try {
            String sql = "UPDATE users SET Password=? WHERE User_Name=? AND Password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, userName);
            ps.setString(3, currentPassword);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(userName + " password UPDATE was successful!");
            } else {
                System.out.println(userName + " password UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int updateUserName(String currentUserName, String newUserName, String password) {
        int rowsAffected = 0;
        try {
            String sql = "UPDATE users SET User_Name=? WHERE User_Name=? AND Password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newUserName);
            ps.setString(2, currentUserName);
            ps.setString(3, password);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(currentUserName + " username UPDATE was successful!");
                System.out.println("New username: " + newUserName);
            } else {
                System.out.println(currentUserName + " username UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int deleteUser(int userId) {
        int rowsAffected = 0;
        try {
            String sql = "DELETE FROM users WHERE User_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User number " + userId + " was successfully deleted!");
            } else {
                System.out.println("User DELETE failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public int addUser(String userName, String password) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO users (User_Name, Password) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User INSERT was successful!");
            } else {
                System.out.println("User INSERT failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }
}
