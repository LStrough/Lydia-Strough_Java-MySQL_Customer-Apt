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
        /*
        //Locale.setDefault(new Locale("fr"));
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();

         */

        JDBC.openConnection();
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        ContactDao contactDao = new ContactDaoImpl();
        //System.out.println(appointmentDao.getAllAppointments());
        //System.out.println(appointmentDao.getAppointment(1));
        System.out.println(appointmentDao.addAppointment(11, 1, 1, "Title", "Description",
               "Location", "Type", '2022-06-15 12:00','2022-06-15 12:30'));
        //System.out.println(contactDao.getAllContacts());

    }
}
