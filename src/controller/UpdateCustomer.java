package controller;

import DAO.*;
import utilities.ListManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateCustomer implements Initializable {
    Stage stage;
    Parent scene;

    public TextField nameTxt, addressTxt, postalCodeTxt, phoneTxt;
    public ComboBox<Country> countryComboBx;
    public ComboBox<Division> divisionComboBx;
    public Label nameE, addressE, postalCodeE, phoneE, countryE, divisionE;

    Customer selCustomer = null;
    public int countryId, divisionId;
    public String customerName, address, postalCode, phone;

    public void updateCustomer(Customer selectedCustomer) {
        JDBC.openConnection();
        CountryDao countryDao = new CountryDaoImpl();

        selCustomer = selectedCustomer;
        nameTxt.setText(String.valueOf(selCustomer.getCustomerName()));
        addressTxt.setText(String.valueOf(selCustomer.getAddress()));
        postalCodeTxt.setText(String.valueOf(selCustomer.getPostalCode()));
        phoneTxt.setText(String.valueOf(selCustomer.getPhone()));

        Country selCountry = null;
        for(Country country : countryDao.getAllCountries()) {
            if(country.getCountryId() == selCustomer.getCountryId()) {
                selCountry = country;
                break;
            }
        }
        countryComboBx.getSelectionModel().select(selCountry);
        countryId = selCountry.getCountryId();

        divisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
        Division selDivision = null;
        for(Division division : ListManager.getFilteredDivisions(countryId)) {
            if(division.getDivisionId() == selCustomer.getDivisionId()) {
                selDivision = division;
                break;
            }
        }
        divisionComboBx.getSelectionModel().select(selDivision);
        divisionId = selDivision.getDivisionId();
    }

    public void onActionUpdateCustomer(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try {
            boolean formatError = false;
            int customerId = selCustomer.getCustomerId();
            customerName = nameTxt.getText();
            address = addressTxt.getText();
            postalCode = postalCodeTxt.getText();
            phone = phoneTxt.getText();
            divisionId = divisionComboBx.getValue().getDivisionId();

            if(customerName.isBlank()) {
                errorMessage(1);
                formatError = true;
            } else if(address.isBlank()) {
                errorMessage(2);
                formatError = true;
            } else if (postalCode.isBlank()){
                errorMessage(3);
                formatError = true;
            }else if (phone.isBlank()){
                errorMessage(4);
                formatError = true;
            }else if (countryComboBx.getSelectionModel() == null){
                errorMessage(5);
                formatError = true;
            }else if (divisionComboBx.getSelectionModel() == null){
                errorMessage(6);
                formatError = true;
            }

            if(!formatError) {
                CustomerDao customerDao = new CustomerDaoImpl();
                customerDao.updateCustomer(customerId, customerName, address, postalCode, phone, divisionId);

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onActionReturnToCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel \"Update Customer\"");
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

    public void onActionSelectCountry(ActionEvent actionEvent) {
        countryId = countryComboBx.getValue().getCountryId();
        divisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
        divisionComboBx.getSelectionModel().selectFirst();
    }

    public void errorMessage(int errorNum){
        switch (errorNum) {
            case 1 -> {
                nameE.setText("Customer \"Name\" cannot be empty!");
            }
            case 2 -> {
                addressE.setText("Customer \"Address\" cannot be empty!");
            }
            case 3 -> {
                postalCodeE.setText("Customer \"Postal Code\" cannot be empty!");
            }
            case 4 -> {
                phoneE.setText("Customer \"Phone Number\" cannot be empty!");
            }
            case 5 -> {
                countryE.setText("You must select a \"Country\".");
            }
            case 6 -> {
                divisionE.setText("You must select a \"Division\".");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Update Customer: I am initialized!");

        JDBC.openConnection();
        CountryDao countryDao = new CountryDaoImpl();

        countryComboBx.setItems(countryDao.getAllCountries());
    }
}
