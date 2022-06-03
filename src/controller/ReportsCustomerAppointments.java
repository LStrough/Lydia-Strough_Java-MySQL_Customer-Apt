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
import java.util.ResourceBundle;

public class ReportsCustomerAppointments implements Initializable {
    Stage stage;
    Parent scene;
    public ToggleGroup viewByTgl;
    public Label viewByLbl;
    public ComboBox viewByComboBx;
    public TableView customerTableView;
    public TableColumn customerIdCol;
    public TableColumn customerNameCol;
    public TableColumn addressCol;
    public TableColumn postalCodeCol;
    public TableColumn phoneNumCol;
    public TableColumn createdDateCol;
    public TableColumn createdByCol;
    public TableColumn lastUpdateCol;
    public TableColumn lastUpdatedByCol;
    public TableColumn stateProvinceCol;
    public Label totalCustomersLbl;

    public void onActionViewByType(ActionEvent actionEvent) {
        System.out.println("View by Type Radio Button Clicked!");

        viewByLbl.setText("Type");
    }

    public void onActionViewByMonth(ActionEvent actionEvent) {
        System.out.println("View by Month Radio Button Clicked!");

        viewByLbl.setText("Month");
    }

    public void onActionViewByCountry(ActionEvent actionEvent) {
        System.out.println("View by Country Radio Button Clicked!");

        viewByLbl.setText("Country");
    }

    public void onActionContactSchedule(ActionEvent actionEvent) throws IOException {
        System.out.println("Contact Schedule Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsContactSchedule.fxml"));
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
        System.out.println("Reports (Customer Appointments): I am initialized!");
    }
}
