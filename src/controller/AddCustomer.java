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
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomer implements Initializable {
    Stage stage;
    Parent scene;

    public TextField nameTxt, addressTxt, postalCodeTxt, phoneTxt;
    public ComboBox<Country> countryComboBx;
    public ComboBox<Division> divisionComboBx;
    public Label nameE, addressE, postalCodeE, phoneE, countryE, divisionE;

    private int countryId;
    public String customerName, address, postalCode, phone;

    public void onActionSaveCustomer(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try {
            boolean formatError = false;
            customerName = nameTxt.getText();
            address = addressTxt.getText();
            postalCode = postalCodeTxt.getText();
            phone = phoneTxt.getText();
            int divisionId = divisionComboBx.getSelectionModel().getSelectedItem().getDivisionId();

            if(customerName.isBlank()) {
                exceptionMessage(1);
                formatError = true;
            } else if(address.isBlank()) {
                exceptionMessage(7);
                exceptionMessage(2);
                formatError = true;
            } else if (postalCode.isBlank()){
                exceptionMessage(8);
                exceptionMessage(3);
                formatError = true;
            }else if (phone.isBlank()){
                exceptionMessage(9);
                exceptionMessage(4);
                formatError = true;
            }else if (countryComboBx.getSelectionModel() == null){
                exceptionMessage(10);
                exceptionMessage(5);
                formatError = true;
            }else if (divisionComboBx.getSelectionModel() == null){
                exceptionMessage(11);
                exceptionMessage(6);
                formatError = true;
            } else {
                exceptionMessage(12);
                formatError = false;
            }

            if(formatError == false) {
                CustomerDao customerDao = new CustomerDaoImpl();
                customerDao.addCustomer(customerName, address, postalCode, phone, divisionId);

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
        alert.setTitle("Cancel \"Add Customer\"");
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
        countryId = countryComboBx.getValue().getCountryId();
        divisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
        divisionComboBx.getSelectionModel().selectFirst();
    }

    public void exceptionMessage(int exceptionNum){
        switch (exceptionNum) {
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
            case 7 -> {
                nameE.setText("");
            }
            case 8 -> {
                addressE.setText("");
            }
            case 9 -> {
                postalCodeE.setText("");
            }
            case 10 -> {
                phoneE.setText("");
            }
            case 11 -> {
                countryE.setText("");
            }
            case 12 -> {
                divisionE.setText("");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Customer: I am initialized!");
        try {
            JDBC.openConnection();
            CountryDao countryDao = new CountryDaoImpl();

            countryComboBx.setItems(countryDao.getAllCountries());
            countryComboBx.getSelectionModel().selectFirst();
            countryId = countryComboBx.getValue().getCountryId();
            divisionComboBx.setItems(ListManager.getFilteredDivisions(countryId));
            divisionComboBx.getSelectionModel().selectFirst();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
