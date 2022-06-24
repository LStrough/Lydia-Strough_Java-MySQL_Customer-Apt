package DAO;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static DAO.JDBC.connection;

/**
 * This is the "login to database" class.
 * This class is for logging into the pre-existing database. 
 *
 * @author Lydia Strough
 */
public class LoginToDB {
    /**
     * This is the "login query" method.
     * This method takes user input (username and password) and validates it.
     *
     * @param userName the username string the user enters as they attempt to log into the database
     * @param password the password string the user enters as they attempt to log into the database
     * @return a user object, if the username and password strings match a user in the database
     */
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

    /**
     * This is the "get login local date and time" method.
     * This method generates the operating systems default local date and time and a variable.
     *
     * @return ldt the local date and time of the operating system
     */
   public static LocalDateTime getLoginLDT(){
       LocalDateTime ldt = LocalDateTime.now(ZoneId.systemDefault());
       return ldt;
   }
}
