package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

/**
 *This is the Report DAO Implementation class.
 * This class Implements the Report DAO class' method definitions.
 *
 * @author Lydia Strough
 */
public class ReportDaoImpl implements ReportDao{
    /**
     * This is the User "all reports" list.
     * */
    ObservableList<Report> allReports = FXCollections.observableArrayList();

    /**
     * This is the get All Reports method.
     * This method accesses the database and returns all appointments, grouped by month and type (each instance is counted as well).
     * Each appointment is then added to an observable list "allReports".
     *
     * @return the allReports list
     */
    @Override
    public ObservableList<Report> getAllReports() {
        try{
            String sql = "SELECT monthname(start), type, count(*) as cnt FROM appointments GROUP BY monthname(start), type";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                String month = result.getString("monthname(start)");
                String type = result.getString("type");
                int count = result.getInt("cnt");
                Report report = new Report(month, type, count);
                allReports.add(report);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return allReports;
    }
}
