package controller;

import DAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Country;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReportsCountry implements Initializable {
    Stage stage;
    Parent scene;

    public TableView<Appointment> reportTableView;
    public TableColumn apptIdCol, titleCol, descriptionCol, locationCol, contactCol, typeCol, startDateCol, endDateCol,
            startTimeCol, endTimeCol, customerIdCol, userIdCol;
    public ComboBox<Country> countryComboBx;
    public Label userTimeZoneLbl;

    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Logout Button Clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Do you wish to Exit the program?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            System.exit(0);
        }
    }

    public void onActionContactReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Contact Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsContact.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionMonthTypeReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Month & Type Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsMonthType.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionPopulateTable(ActionEvent actionEvent) {
        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoImpl();
        //reportTableView.setItems(apptDao.);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports (Country): I am Initialized!");

        userTimeZoneLbl.setText("Your Time Zone: " + String.valueOf(ZoneId.systemDefault()));

        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        JDBC.openConnection();
        CountryDao countryDao = new CountryDaoImpl();
        countryComboBx.setItems(countryDao.getAllCountries());
    }
}
