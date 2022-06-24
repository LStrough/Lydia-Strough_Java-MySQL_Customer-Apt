package DAO;

import javafx.collections.ObservableList;
import model.Report;

/**
 *This is the Report DAO class.
 * This class acts as an Interface for the Report DAO Implementation class.
 *
 * @author Lydia Strough
 */
public interface ReportDao {

    /**
     * This is the get All Reports method.
     * This method accesses the database and returns all appointments, grouped by month and type (each instance is counted as well).
     * Each appointment is then added to an observable list "allReports".
     *
     * @return the allReports list
     */
    public ObservableList<Report> getAllReports();
}
