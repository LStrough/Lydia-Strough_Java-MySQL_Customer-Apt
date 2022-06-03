package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportContactSchedule implements Initializable {
    Stage stage;
    Parent scene;
    public ComboBox contactComboBx;
    public TableView apptTableView;
    public TableColumn apptIdCol;
    public TableColumn titleCol;
    public TableColumn descriptionCol;
    public TableColumn typeCol;
    public TableColumn startDateCol;
    public TableColumn endDateCol;
    public TableColumn startTimeCol;
    public TableColumn endTimeCol;
    public TableColumn customerIdCol;

    public void onActionCustomerAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Customer Appointments Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportCustomerAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Logout Button Clicked!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports (Contact Schedule): I am initialized!");
    }
}
