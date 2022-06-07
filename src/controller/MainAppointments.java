package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class MainAppointments implements Initializable {
    Stage stage;
    Parent scene;
    public ToggleGroup viewByTgl;
    public DatePicker searchApptByDate;
    public TableView apptTableView;
    public TableColumn apptIdCol;
    public TableColumn titleCol;
    public TableColumn descriptionCol;
    public TableColumn locationCol;
    public TableColumn contactCol;
    public TableColumn typeCol;
    public TableColumn startDateCol;
    public TableColumn endDateCol;
    public TableColumn startTimeCol;
    public TableColumn endTimeCol;
    public TableColumn customerIdCol;
    public TableColumn userIdCol;
    public Label userTimeZoneLbl;

    public void onActionViewByWeek(ActionEvent actionEvent) {
        System.out.println("View by Week Radio Button Clicked!");
    }

    public void onActionViewByMonth(ActionEvent actionEvent) {
        System.out.println("View by Month Radio Button Clicked!");
    }

    public void onActionViewAll(ActionEvent actionEvent) {
        System.out.println("View All Radio Button Clicked!");
    }

    public void onActionSearchApptByDate(ActionEvent actionEvent) {
    }

    public void onActionAddAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Appointment Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionUpdateAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Appointment Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeleteAppt(ActionEvent actionEvent) {
        System.out.println("Delete Appointment Button Clicked!");
    }

    public void onActionCustomers(ActionEvent actionEvent) throws IOException {
        System.out.println("Customers Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionReports(ActionEvent actionEvent) throws IOException {
        System.out.println("Reports Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportCustomerAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Logout Button Clicked!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Appointment Schedule (Main Menu): I am initialized!");

        userTimeZoneLbl.setText("Your Time Zone: " + String.valueOf(ZoneId.systemDefault()));
    }
}
