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
import model.Country;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReportsCountry implements Initializable {
    Stage stage;
    Parent scene;

    public TableView<Customer> reportTableView;
    public TableColumn customerIdCol, customerNameCol, addressCol, postalCodeCol, phoneNumCol, countryCol;
    public ComboBox<Country> countryComboBx;
    public Label totalCustomersLbl;

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
        CustomerDao custDao = new CustomerDaoImpl();
        int countryId = countryComboBx.getSelectionModel().getSelectedItem().getCountryId();
        reportTableView.setItems(custDao.getCustomersByCountry(countryId));

        totalCustomersLbl.setText("Total Customers: " + custDao.getCustomersByCountry(countryId).size());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports (Country): I am Initialized!");

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));

        JDBC.openConnection();
        CountryDao countryDao = new CountryDaoImpl();
        countryComboBx.setItems(countryDao.getAllCountries());
    }
}
