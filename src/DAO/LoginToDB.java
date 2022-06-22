package DAO;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static DAO.JDBC.connection;

public class LoginToDB {
   public static User loginQuery(String userName, String password) {
       try {
           String sql = "SELECT * FROM users WHERE User_Name=? AND Password=?";
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setString(1, userName);
           ps.setString(2, password);

           ResultSet result = ps.executeQuery();
           User userResult = null;
           if (result.next()) {
               int userId = result.getInt("User_ID");
               userName = result.getString("User_Name");
               password = result.getString("Password");
               userResult = new User(userId, userName, password);
           }
           return userResult;
       }catch(Exception e){
           System.out.println("Error: " + e.getMessage());
       }
       return null;
   }
   public static LocalDateTime getLoginLDT(){
       LocalDateTime ldt = LocalDateTime.now(ZoneId.systemDefault());
       return ldt;
   }
}
