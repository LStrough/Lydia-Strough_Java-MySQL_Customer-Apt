package controller;

import DAO.*;
import Utilities.ListManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {
    Stage stage;
    Parent scene;
    public TextField customerIdTxt;
    public TextField customerNameTxt;
    public TextField customerAddressTxt;
    public TextField customerPostalCodeTxt;
    public TextField customerPhoneNumTxt;
    public ComboBox<Country> customerCountryComboBx;
    public ComboBox<Division> customerDivisionComboBx;
    public Label customerNameE;
    public Label customerAddressE;
    public Label customerPostalCodeE;
    public Label customerPhoneNumE;
    private int countryId;
    public String customerName, address, postalCode, phone;

    public void onActionSaveCustomer(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try {
            CustomerDao customerDao = new CustomerDaoImpl();

            customerName = customerNameTxt.getText();
            address = customerAddressTxt.getText();
            postalCode = customerPostalCodeTxt.getText();
            phone = customerPhoneNumTxt.getText();
            int divisionId = customerDivisionComboBx.getSelectionModel().getSelectedItem().getDivisionId();

            customerDao.addCustomer(customerName, address, postalCode, phone, divisionId);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            JDBC.closeConnection();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void onActionReturnToCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("All changes will be forgotten, do you wish to continue?");
        alert.showAndWait();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
           stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
           scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
           stage.setScene(new Scene(scene));
           stage.show();
           JDBC.closeConnection();
        }
    }

    public void onActionSelectCountry(ActionEvent actionEvent) {
        countryId = customerCountryComboBx.getValue().getCountryId();

        customerDivisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
        customerDivisionComboBx.getSelectionModel().selectFirst();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Customer: I am initialized!");
        try {
            JDBC.openConnection();
            CountryDao countryDao = new CountryDaoImpl();

            customerCountryComboBx.setItems(countryDao.getAllCountries());
            customerCountryComboBx.getSelectionModel().selectFirst();

            countryId = customerCountryComboBx.getValue().getCountryId();

            customerDivisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
            customerDivisionComboBx.getSelectionModel().selectFirst();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
