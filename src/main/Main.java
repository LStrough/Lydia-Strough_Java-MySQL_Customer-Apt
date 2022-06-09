package main;

import DAO.JDBC;
import DAO.UserDao;
import DAO.UserDaoImpl;
import DAO.UserDaoImplv2;
import helper.UserCRUD;
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
        UserDao userDao = new UserDaoImpl();
        UserDao userDaov2 = new UserDaoImplv2();
        userDao.display();
        userDaov2.display();

        UserCRUD.getAllUsers();



        launch(args);
        JDBC.closeConnection();
    }
}
