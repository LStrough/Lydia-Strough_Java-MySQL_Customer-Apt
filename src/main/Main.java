package main;

import DAO.JDBC;
import DAO.UserDao;
import DAO.UserDaoImpl;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Appointment Management System");
        stage.setScene(new Scene(root, 1200, 542));
        stage.show();
    }

    public static void main(String[] args) {
        //Locale.setDefault(new Locale("fr"));

        JDBC.openConnection();
        /*
        UserDao userDao = new UserDaoImpl();                  //how to use DaoImpl methods
        userDao.display();
         */

        UserDao userDao = new UserDaoImpl();
        //userDao.getUser(1);                                   //returns: class name @ hashcode (pointer reference in memory of object)
                                                                //@Override toString()
                                                                // create toString() in model.User class and format text how you want!

        System.out.println(userDao.getUser(1));

        launch(args);
        JDBC.closeConnection();
    }
}
