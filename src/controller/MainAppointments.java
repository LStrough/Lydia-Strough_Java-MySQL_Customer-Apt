package controller;

import DAO.AppointmentDao;
import DAO.AppointmentDaoImpl;
import DAO.JDBC;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Optional;
import java.util.ResourceBundle;

public class MainAppointments implements Initializable {
    Stage stage;
    Parent scene;

    public ToggleGroup viewByTgl;
    public DatePicker datePicker;
    public TableView<Appointment> apptTableView;
    public TableColumn apptIdCol, titleCol, descriptionCol, locationCol, contactCol, typeCol, startDateCol, endDateCol,
            startTimeCol, endTimeCol, customerIdCol, userIdCol;
    public Label userTimeZoneLbl;

    public void onActionViewByWeek(ActionEvent actionEvent) {
        System.out.println("View by Week Radio Button Clicked!");

        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoImpl();
        //apptTableView.setItems(apptDao.upcomingApptsWeek(LocalDateTime startOfToday));
    }

    public void onActionViewByMonth(ActionEvent actionEvent) {
        System.out.println("View by Month Radio Button Clicked!");

        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoImpl();
        //apptTableView.setItems(apptDao.upcomingApptsMonth(LocalDateTime startOfToday));
    }

    public void onActionViewAll(ActionEvent actionEvent) {
        System.out.println("View All Radio Button Clicked!");

        JDBC.openConnection();
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        apptTableView.setItems(appointmentDao.getAllAppointments());
    }

    public void onActionSearchApptByDate(ActionEvent actionEvent) {
        JDBC.openConnection();
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        apptTableView.setItems(appointmentDao.getAllAppointments());
        LocalDate selDate = datePicker.getValue();

        try{
            ObservableList<Appointment> appts = appointmentDao.lookUpAppointment(selDate);
            apptTableView.setItems(appts);
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if(!((AppointmentDaoImpl) appointmentDao).apptFound) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No item was found.");
            alert.showAndWait();
        }
    }

    public void onActionAddAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Appointment Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionUpdateAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Appointment Button Clicked!");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateAppointment.fxml"));
            Parent scene = loader.load();

            UpdateAppointment updateApptController = loader.getController();

            Appointment selectedAppt = apptTableView.getSelectionModel().getSelectedItem();

            updateApptController.updateAppointment(selectedAppt);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Appointment Selected");
            alert.setContentText("Please select an appointment to update!");
            alert.showAndWait();
        }
    }

    public void onActionDeleteAppt(ActionEvent actionEvent) {
        System.out.println("Delete Appointment Button Clicked!");

        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoImpl();
        Appointment selAppt = apptTableView.getSelectionModel().getSelectedItem();
        int appointmentId = selAppt.getAppointmentId();
        int customerId = selAppt.getCustomerId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("The selected \"Appointment\" will be deleted. Do you wish to continue?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            System.out.println(apptDao.deleteAppointment(appointmentId, customerId));

            JDBC.openConnection();
            apptTableView.setItems(apptDao.getAllAppointments());
        }
    }

    public void onActionCustomers(ActionEvent actionEvent) throws IOException {
        System.out.println("Customers Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainCustomers.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionReports(ActionEvent actionEvent) throws IOException {
        System.out.println("Reports Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsContact.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Appointment Schedule (Main Menu): I am initialized!");

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
        AppointmentDao appointmentDao = new AppointmentDaoImpl();
        apptTableView.setItems(appointmentDao.getAllAppointments());

        LocalDateTime loginLDT = DAO.LoginToDB.getLoginLDT();
        System.out.println("Login local date: " + DAO.LoginToDB.getLoginLDT());
        appointmentDao.upcomingApptAlert(loginLDT);
    }
}
