package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class UserDaoImpl implements UserDao{
    ObservableList<User> users = FXCollections.observableArrayList();

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
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return users;
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
    public void updateUser(int index, User newUser) {
        //mySQL update database!
    }

    @Override
    public boolean deleteUser(User selectedUser) {
        //mySQL delete from database!
        return false;
    }

    @Override
    public void addUser(User user){
        //mySQL add to database!
    }
}
