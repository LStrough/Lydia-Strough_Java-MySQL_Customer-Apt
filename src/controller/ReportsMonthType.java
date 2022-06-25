package controller;

import DAO.JDBC;
import DAO.ReportDao;
import DAO.ReportDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Report;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the "Month &amp; Type Report" controller.
 *
 * <p>This page displays a list of the databases' tallied list of current appointments, grouped by start date month and
 * appointment type. This page also gives the user the ability to navigate the reports offered in the program. The user
 * can also exit the program from this page or return to the Main Appointments Menu page.</p>
 *
 * @author Lydia Strough
 */
public class ReportsMonthType implements Initializable {
    private Stage stage;
    private Parent scene;
    /**
     * This is the Month &amp; Type report table
     */
    public TableView<Report> reportTableView;
    /**
     * This is the start date month column
     */
    public TableColumn monthCol;
    /**
     * This is the appointment type column
     */
    public TableColumn typeCol;
    /**
     * This is the appointment count column
     */
    public TableColumn countCol;

    /**
     * This is the "Return to Main Appointments" method.
     *
     * <p>The scene shifts from the Month &amp; Type Report screen, to the Main Appointments Menu. </p>
     *
     * @param actionEvent the cancel button is clicked
     * @throws IOException
     */
    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainAppointments.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "Logout" method.
     *
     * <p>A confirmation dialog box populates: "Do you wish to Exit the program?".
     * If the user hits the OK button, the program ends. If cancel is selected, the user
     * stays in the Month &amp; Type Report page.</p>
     *
     * @param actionEvent the logout button is clicked
     */
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

    /**
     * This is the "Contact Report" method.
     *
     * <p>The scene shifts from the Month &amp; Type Report screen, to the Contact Report page. </p>
     *
     * @param actionEvent the contact report button is clicked
     * @throws IOException
     */
    public void onActionContactReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Contact Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsContact.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the "Country Report" method.
     *
     * <p>The scene shifts from the Month &amp; Type screen, to the Country Report page. </p>
     *
     * @param actionEvent the country report button is clicked
     * @throws IOException
     */
    public void onActionCountryReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Country Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportsCountry.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is the initialize method.
     *
     * <p>This is the first method to run when the Month &amp; Type Report page is populated.</p>
     *
     * <p>monthCol is assigned with the month value.</p>
     * <p>typeCol is assigned with the type value.</p>
     * <p>countCol is assigned with the count value.</p>
     *
     * <p>The database connection is opened, and the reportTableView calls the "getAllReports" method from the ReportDao
     * class and populates all reports in the report table.</p>
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports (Month & Type): I am Initialized!");

        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        JDBC.openConnection();
        ReportDao reportDao = new ReportDaoImpl();
        reportTableView.setItems(reportDao.getAllReports());
    }
}
