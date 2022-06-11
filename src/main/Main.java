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
        CountryDao countryDao = new CountryDaoImpl();
        DivisionDao divisionDao = new DivisionDaoImpl();

        //System.out.println(countryDao.getAllCountries());
        //System.out.println(countryDao.getCountry(2));
        //System.out.println(divisionDao.getDivisionsByCountry(2));
        //countryDao.addCountry("Japan");
        //System.out.println(countryDao.getAllCountries());
        //System.out.println(countryDao.getCountry(4));
        //System.out.println(divisionDao.getDivisionsByCountry(4));
        //divisionDao.addDivision("Hokkaido", 4);
        //System.out.println(divisionDao.getDivisionsByCountry(4));
        //countryDao.addCountry("China");
        //System.out.println(countryDao.getAllCountries());
        //System.out.println(countryDao.getCountry(6));
        //System.out.println(divisionDao.getDivisionsByCountry(6));
        //countryDao.deleteCountry(6,"China");               //China success
        //countryDao.deleteCountry(4, "Japan");               //Japan FAIL
        //divisionDao.deleteDivision(3980, "Hokkaido");
        //countryDao.deleteCountry(4, "Japan");                 //Japan success
    }
}
