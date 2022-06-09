package main;

import DAO.*;
import javafx.application.Application;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

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

        /*
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
         */

        /*
        JDBC.openConnection();
        CustomerDao customerDao = new CustomerDaoImpl();

        System.out.println(customerDao.getAllCustomers());
        System.out.println(customerDao.getCustomer(1));
        System.out.println(customerDao.getCustomer(2));
         */

        /*
        JDBC.openConnection();
        try{
            UserDao userDao = new UserDaoImpl();
            ObservableList<User> users = userDao.getAllUsers();
            int userId = 0;
            String userName = "riki";                           //textFieldName.getText()
            String password = "riki";

            for(int i = 0; i <= users.size(); ++i) {
                userId = i + 1;
            }

            userDao.addUser(new User(userId, userName, password));
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
         */
    }
}
