package controller;

import DAO.*;
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
    public ComboBox<Division> customerDivisionComboBx;                                  //cast
    public Label customerNameE;
    public Label customerAddressE;
    public Label customerPostalCodeE;
    public Label customerPhoneNumE;
    public Label customerCountryE;
    public Label customerDivisionE;

    public void onActionSaveCustomer(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try {
            String customerName = customerNameTxt.getText();
            String address = customerAddressTxt.getText();
            String postalCode = customerPostalCodeTxt.getText();
            String phone = customerPhoneNumTxt.getText();
            int divisionId = customerDivisionComboBx.getValue().getDivisionId();

            if(customerName.isEmpty()){
                customerNameE.setText("Customer name cannot be empty!");
            }
            if(address.isEmpty()){
                customerAddressE.setText("Customer address cannot be empty!");
            }
            if(postalCode.isEmpty()){
                customerPostalCodeE.setText("Customer postal code cannot be empty!");
            }
            if(phone.isEmpty()){
                customerPhoneNumE.setText("Customer phone number cannot be empty!");
            }

            JDBC.openConnection();
            CustomerDao customerDao = new CustomerDaoImpl();
            customerDao.addCustomer(customerName, address, postalCode, phone, divisionId);

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainCustomer.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter/select a valid value for each field!");
            alert.showAndWait();
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
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Customer: I am initialized!");

        JDBC.openConnection();
        CountryDao countryDao = new CountryDaoImpl();
        DivisionDao divisionDao = new DivisionDaoImpl();
        customerCountryComboBx.setPromptText("You must choose a Country...");
        customerCountryComboBx.setItems(countryDao.getAllCountries());
        customerCountryComboBx.setVisibleRowCount(5);

        int countryId = customerCountryComboBx.getValue().getCountryId();
        if(countryId > 0){
            customerDivisionComboBx.setPromptText("You must choose a Division...");
            customerDivisionComboBx.setItems(divisionDao.getDivisionsByCountry(countryId));
            customerDivisionComboBx.setVisibleRowCount(5);
        }
    }
}
