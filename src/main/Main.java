package main;

import DAO.*;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BusinessHour;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Appointment Management System");
        stage.setScene(new Scene(root, 1200, 542));
        stage.show();
    }

    public static void businessHourInit() { //08:00 - 22:00
        BusinessHour bHr0800= new BusinessHour(8, 00);
        BusinessHour bHr0815= new BusinessHour(8, 15);
        BusinessHour bHr0830= new BusinessHour(8, 30);
        BusinessHour bHr0845= new BusinessHour(8, 45);

        BusinessHour.addBusinessHour(bHr0800);
        BusinessHour.addBusinessHour(bHr0815);
        BusinessHour.addBusinessHour(bHr0830);
        BusinessHour.addBusinessHour(bHr0845);

        BusinessHour bHr0900= new BusinessHour(9, 00);
        BusinessHour bHr0915= new BusinessHour(9, 15);
        BusinessHour bHr0930= new BusinessHour(9, 30);
        BusinessHour bHr0945= new BusinessHour(9, 45);

        BusinessHour.addBusinessHour(bHr0900);
        BusinessHour.addBusinessHour(bHr0915);
        BusinessHour.addBusinessHour(bHr0930);
        BusinessHour.addBusinessHour(bHr0945);

        BusinessHour bHr1000= new BusinessHour(10, 00);
        BusinessHour bHr1015= new BusinessHour(10, 15);
        BusinessHour bHr1030= new BusinessHour(10, 30);
        BusinessHour bHr1045= new BusinessHour(10, 45);

        BusinessHour.addBusinessHour(bHr1000);
        BusinessHour.addBusinessHour(bHr1015);
        BusinessHour.addBusinessHour(bHr1030);
        BusinessHour.addBusinessHour(bHr1045);

        BusinessHour bHr1100= new BusinessHour(11, 00);
        BusinessHour bHr1115= new BusinessHour(11, 15);
        BusinessHour bHr1130= new BusinessHour(11, 30);
        BusinessHour bHr1145= new BusinessHour(11, 45);

        BusinessHour.addBusinessHour(bHr1100);
        BusinessHour.addBusinessHour(bHr1115);
        BusinessHour.addBusinessHour(bHr1130);
        BusinessHour.addBusinessHour(bHr1145);

        BusinessHour bHr1200= new BusinessHour(12, 00);
        BusinessHour bHr1215= new BusinessHour(12, 15);
        BusinessHour bHr1230= new BusinessHour(12, 30);
        BusinessHour bHr1245= new BusinessHour(12, 45);

        BusinessHour.addBusinessHour(bHr1200);
        BusinessHour.addBusinessHour(bHr1215);
        BusinessHour.addBusinessHour(bHr1230);
        BusinessHour.addBusinessHour(bHr1245);

        BusinessHour bHr1300= new BusinessHour(13, 00);
        BusinessHour bHr1315= new BusinessHour(13, 15);
        BusinessHour bHr1330= new BusinessHour(13, 30);
        BusinessHour bHr1345= new BusinessHour(13, 45);

        BusinessHour.addBusinessHour(bHr1300);
        BusinessHour.addBusinessHour(bHr1315);
        BusinessHour.addBusinessHour(bHr1330);
        BusinessHour.addBusinessHour(bHr1345);

        BusinessHour bHr1400= new BusinessHour(14, 00);
        BusinessHour bHr1415= new BusinessHour(14, 15);
        BusinessHour bHr1430= new BusinessHour(14, 30);
        BusinessHour bHr1445= new BusinessHour(14, 45);

        BusinessHour.addBusinessHour(bHr1400);
        BusinessHour.addBusinessHour(bHr1415);
        BusinessHour.addBusinessHour(bHr1430);
        BusinessHour.addBusinessHour(bHr1445);

        BusinessHour bHr1500= new BusinessHour(15, 00);
        BusinessHour bHr1515= new BusinessHour(15, 15);
        BusinessHour bHr1530= new BusinessHour(15, 30);
        BusinessHour bHr1545= new BusinessHour(15, 45);

        BusinessHour.addBusinessHour(bHr1500);
        BusinessHour.addBusinessHour(bHr1515);
        BusinessHour.addBusinessHour(bHr1530);
        BusinessHour.addBusinessHour(bHr1545);

        BusinessHour bHr1600= new BusinessHour(16, 00);
        BusinessHour bHr1615= new BusinessHour(16, 15);
        BusinessHour bHr1630= new BusinessHour(16, 30);
        BusinessHour bHr1645= new BusinessHour(16, 45);

        BusinessHour.addBusinessHour(bHr1600);
        BusinessHour.addBusinessHour(bHr1615);
        BusinessHour.addBusinessHour(bHr1630);
        BusinessHour.addBusinessHour(bHr1645);

        BusinessHour bHr1700= new BusinessHour(17, 00);
        BusinessHour bHr1715= new BusinessHour(17, 15);
        BusinessHour bHr1730= new BusinessHour(17, 30);
        BusinessHour bHr1745= new BusinessHour(17, 45);

        BusinessHour.addBusinessHour(bHr1700);
        BusinessHour.addBusinessHour(bHr1715);
        BusinessHour.addBusinessHour(bHr1730);
        BusinessHour.addBusinessHour(bHr1745);

        BusinessHour bHr1800= new BusinessHour(18, 00);
        BusinessHour bHr1815= new BusinessHour(18, 15);
        BusinessHour bHr1830= new BusinessHour(18, 30);
        BusinessHour bHr1845= new BusinessHour(18, 45);

        BusinessHour.addBusinessHour(bHr1800);
        BusinessHour.addBusinessHour(bHr1815);
        BusinessHour.addBusinessHour(bHr1830);
        BusinessHour.addBusinessHour(bHr1845);

        BusinessHour bHr1900= new BusinessHour(19, 00);
        BusinessHour bHr1915= new BusinessHour(19, 15);
        BusinessHour bHr1930= new BusinessHour(19, 30);
        BusinessHour bHr1945= new BusinessHour(19, 45);

        BusinessHour.addBusinessHour(bHr1900);
        BusinessHour.addBusinessHour(bHr1915);
        BusinessHour.addBusinessHour(bHr1930);
        BusinessHour.addBusinessHour(bHr1945);

        BusinessHour bHr2000= new BusinessHour(20, 00);
        BusinessHour bHr2015= new BusinessHour(20, 15);
        BusinessHour bHr2030= new BusinessHour(20, 30);
        BusinessHour bHr2045= new BusinessHour(20, 45);

        BusinessHour.addBusinessHour(bHr2000);
        BusinessHour.addBusinessHour(bHr2015);
        BusinessHour.addBusinessHour(bHr2030);
        BusinessHour.addBusinessHour(bHr2045);

        BusinessHour bHr2100= new BusinessHour(21, 00);
        BusinessHour bHr2115= new BusinessHour(21, 15);
        BusinessHour bHr2130= new BusinessHour(21, 30);
        BusinessHour bHr2145= new BusinessHour(21, 45);

        BusinessHour.addBusinessHour(bHr2100);
        BusinessHour.addBusinessHour(bHr2115);
        BusinessHour.addBusinessHour(bHr2130);
        BusinessHour.addBusinessHour(bHr2145);

        BusinessHour bHr2200= new BusinessHour(22, 00);

        BusinessHour.addBusinessHour(bHr2200);
    }

    public static void main(String[] args) {
        //Locale.setDefault(new Locale("fr"));
        businessHourInit();

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();


        //System.out.println(ZoneId.systemDefault());
        //ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);
        //ZoneId.getAvailableZoneIds().stream().filter(z->z.contains("America")).sorted().forEach(System.out::println);
/*
        LocalDate myLD = LocalDate.of(2022, 6, 15);
        LocalTime myLT = LocalTime.of(14, 30);                             //create comboBox for each hour and minute?
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
        ZoneId utcZoneId = ZoneId.of("UTC");                                     //UTC zoneId
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);   //convert from local time to UTC
        System.out.println("User time to UTC: " + utcZDT);
        myZDT = ZonedDateTime.ofInstant(utcZDT.toInstant(), myZoneId);                  //convert UTC back to local time
        System.out.println("UTC to User time: " + myZDT);

         */
    }
}
