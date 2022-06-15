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
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
/*
        JDBC.openConnection();
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        ContactDao contactDao = new ContactDaoImpl();
        //System.out.println(appointmentDao.getAllAppointments());
        //System.out.println(appointmentDao.getAppointment(1));
        System.out.println(appointmentDao.addAppointment(11, 1, 1, "Title", "Description",
               "Location", "Type", '2022-06-15 12:00','2022-06-15 12:30'));
        //System.out.println(contactDao.getAllContacts());

 */
        /*
        System.out.println(ZoneId.systemDefault());
        ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);
        ZoneId.getAvailableZoneIds().stream().filter(z->z.contains("America")).sorted().forEach(System.out::println);

        LocalDate myLD = LocalDate.of(2022, 6, 6);
        LocalTime myLT = LocalTime.of(17, 45);                                          //create comboBox for each hour and minute?
        LocalDateTime myLDT = LocalDateTime.of(myLD, myLT);
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        System.out.println(myZDT);                                                      //date,(T)localTime,GMT time, system default timezone
        System.out.println(myZDT.toLocalDate());
        System.out.println(myZDT.toLocalTime());
        System.out.println(myZDT.toLocalDate().toString() + " " + myZDT.toLocalTime().toString());

         */

        /*
        LocalDate myLD = LocalDate.of(2022, 6, 6);
        LocalTime myLT = LocalTime.of(17, 45);
        LocalDateTime myLDT = LocalDateTime.of(myLD, myLT);
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, myZoneId);
        System.out.println("User time: " + myZDT);                                      //local time
        ZoneId utcZoneId = ZoneId.of("UTC");                                            //UTC zoneId
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);   //convert from local time to UTC
        System.out.println("User time to UTC: " + utcZDT);
        myZDT = ZonedDateTime.ofInstant(utcZDT.toInstant(), myZoneId);                  //convert UTC back to local time
        System.out.println("UTC to User time: " + myZDT);

         */


    }
}
