package main;

import DAO.*;
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
        /*
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
         */
        JDBC.openConnection();
        DivisionDao divisionDao = new DivisionDaoImpl();
        System.out.println();
        System.out.println();

        /*
        divisionDao.getAllDivisions();
        divisionDao.getDivision(1);
        divisionDao.getDivisionsByCountry(2);
        divisionDao.addDivision("Lydia's Place", 2);
        divisionDao.updateDivisionName("Lydia's Place", 2, "Lydia");
        divisionDao.updateDivisionName("Lydia's Place", 1, "Lydia");            // test fail FIRST
        divisionDao.updateDivisionCountry("Lydia", 2, 3);
        divisionDao.getDivisionsByCountry(2);
        divisionDao.getDivisionsByCountry(3);
        divisionDao.deleteDivision(1, "Lydia");                                 //get divisionId based on DB
         */
    }
}
