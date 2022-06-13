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
    public ComboBox<Division> customerDivisionComboBx;
    public Label customerNameE;
    public Label customerAddressE;
    public Label customerPostalCodeE;
    public Label customerPhoneNumE;
    public Label customerCountryE;
    public Label customerDivisionE;
    private int countryId = 0;
    private int divisionId = 0;
    public String customerName, address, postalCode, phone;

    public void onActionSaveCustomer(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try {
            JDBC.openConnection();
            CustomerDao customerDao = new CustomerDaoImpl();
            DivisionDao divisionDao = new DivisionDaoImpl();
            customerName = customerNameTxt.getText();
            address = customerAddressTxt.getText();
            postalCode = customerPostalCodeTxt.getText();
            phone = customerPhoneNumTxt.getText();
            countryId = customerCountryComboBx.getValue().getCountryId();

            if (countryId > 0){
                customerDivisionComboBx.setPromptText("You must choose a Division...");
                customerDivisionComboBx.setItems(divisionDao.getDivisionsByCountry(countryId));
                customerDivisionComboBx.setVisibleRowCount(5);
                divisionId = customerDivisionComboBx.getValue().getDivisionId();
            }
            if ((divisionId > 0)) {
                customerDao.addCustomer(customerName, address, postalCode, phone, divisionId);

                stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());

            if(customerName.isEmpty()){
                customerNameE.setText("Customer name cannot be empty!");
            }
            if(!customerName.isEmpty()){
                customerNameE.setText("");
            }
            if(address.isEmpty()){
                customerAddressE.setText("Customer address cannot be empty!");
            }if(!address.isEmpty()){
                customerAddressE.setText("");
            }
            if(postalCode.isEmpty()){
                customerPostalCodeE.setText("Customer postal code cannot be empty!");
            }
            if(!postalCode.isEmpty()){
                customerPostalCodeE.setText("");
            }
            if(phone.isEmpty()){
                customerPhoneNumE.setText("Customer phone number cannot be empty!");
            }
            if(!phone.isEmpty()){
                customerPhoneNumE.setText("");
            }
            if(countryId == 0){
                customerCountryE.setText("You must select a country.");
            }
            if(countryId != 0){
                customerCountryE.setText("");
            }
            if(divisionId == 0){
                customerCountryE.setText("You must select a division.");
            }
            if(divisionId != 0){
                customerCountryE.setText("");
            }
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

        try {
            JDBC.openConnection();
            CountryDao countryDao = new CountryDaoImpl();
            customerCountryComboBx.setPromptText("You must choose a Country...");
            customerCountryComboBx.setItems(countryDao.getAllCountries());
            customerCountryComboBx.setVisibleRowCount(5);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
