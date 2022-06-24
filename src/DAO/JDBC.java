package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This is the "Java database connectivity (JDBC)" class.
 * This class contains methods that open and close a connection from the IDE to the database.
 *
 * @author Lydia Strough
 */
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;

    /**
     * This is the "open database connection" method.
     * This method opens a connection between the Java IDE and the mySQL database.
     */
    public static void openConnection(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection Successful!");
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * This is the "close database connection" method.
     * This method closes the connection between the Java IDE and the mySQL database.
     */
    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("Connection Closed!");
        }catch(Exception e) {
            //do nothing
        }
    }
}
