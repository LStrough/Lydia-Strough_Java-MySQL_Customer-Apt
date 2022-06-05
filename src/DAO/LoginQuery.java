package DAO;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static DAO.JDBC.connection;

public class LoginQuery {
    private static Statement stmt;
    private static ResultSet result;

   public static User loginQuery(String userName, String password) {
       try {
           stmt = connection.createStatement();
           String sql = "SELECT * FROM USERS WHERE User_Name=?, AND Password=?";
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setString(1, userName);
           ps.setString(2, password);

           result = ps.executeQuery();
           User userResult = null;
           if (result.next()) {
               int userId = result.getInt("User_ID");
               userName = result.getString("User_Name");
               password = result.getString("Password");
               userResult = new User(userId, userName, password);
           }
           JDBC.closeConnection();
           return userResult;
       }catch(Exception e){
           System.out.println("Error: " + e.getMessage());
       }
       return null;
   }
}
