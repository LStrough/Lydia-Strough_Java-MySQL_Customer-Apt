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
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateCustomer implements Initializable {
    Stage stage;
    Parent scene;
    Customer selCustomer = null;
    public TextField customerIdTxt;
    public TextField customerNameTxt;
    public TextField customerAddressTxt;
    public TextField customerPostalCodeTxt;
    public TextField customerPhoneNumTxt;
    public ComboBox customerCountryComboBx;
    public ComboBox customerDivisionComboBx;
    public Label customerNameE;
    public Label customerAddressE;
    public Label customerPostalCodeE;
    public Label customerPhoneNumE;
    public int countryId;
    public String customerName, address, postalCode, phone;

    public void updateCustomer(Customer selectedCustomer) {
        JDBC.openConnection();
        CountryDao countryDao = new CountryDaoImpl();

        selCustomer = selectedCustomer;

        customerNameTxt.setText(String.valueOf(selCustomer.getCustomerName()));
        customerAddressTxt.setText(String.valueOf(selCustomer.getAddress()));
        customerPostalCodeTxt.setText(String.valueOf(selCustomer.getPostalCode()));
        customerPhoneNumTxt.setText(String.valueOf(selCustomer.getPhone()));

        customerCountryComboBx.setItems(countryDao.getAllCountries());
        customerCountryComboBx.getSelectionModel().select(selCustomer.getCountryId() - 1);
        countryId = selCustomer.getCountryId();

        customerDivisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
        customerDivisionComboBx.getSelectionModel().select(selCustomer.getDivisionId() - 1);
    }

    public void onActionUpdateCustomer(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");
/*
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
 */
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
       /*
        countryId = customerCountryComboBx.getValue().getCountryId();

        customerDivisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
        customerDivisionComboBx.getSelectionModel().selectFirst();
        */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Customer: I am initialized!");
    }
}
