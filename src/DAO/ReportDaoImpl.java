package DAO;

import javafx.collections.ObservableList;
import model.Report;

public class ReportDaoImpl implements ReportDao{

    @Override
    public ObservableList<Report> getAllReports() {

        /*
        SELECT monthname(start), type, count(*) as cnt FROM
        appointments
        GROUP BY monthname(start), type;
         */

        return null;
    }
}
