package main;

import DAO.*;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Division;

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

        JDBC.openConnection();
        CustomerDao customerDao = new CustomerDaoImpl();
        DivisionDao divisionDao = new DivisionDaoImpl();
        //System.out.println(customerDao.getAllCustomers());
        //System.out.println(divisionDao.getDivisionsByCountry(1));
        //System.out.println(divisionDao.getDivision()); //WA
        //System.out.println(customerDao.getCustomer(1));
        //customerDao.addCustomer("Lydia", "12228 56th Drive NE", "98271", "867-5309", ""); //washington division id
        //System.out.println(customerDao.getCustomer());
    }
}
