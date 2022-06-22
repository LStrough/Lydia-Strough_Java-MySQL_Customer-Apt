package DAO;

import javafx.collections.ObservableList;
import model.Report;

public interface ReportDao {

    public ObservableList<Report> getAllReports();
}
