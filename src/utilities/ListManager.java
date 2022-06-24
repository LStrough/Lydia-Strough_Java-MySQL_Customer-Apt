package utilities;

import DAO.DivisionDao;
import DAO.DivisionDaoImpl;
import javafx.collections.ObservableList;
import model.Division;

/**
 * This class is for managing lists of divisions.
 *
 * @author Lydia Strough
 */
public class ListManager {
    /**
     * This is the get filtered divisions method.
     * This method calls the "get divisions by country" method from the Division DAO and returns a list of divisions.
     *
     * @param countryId the country ID in question
     * @return the divisions(s) associated with the country ID
     */
    public static ObservableList<Division> getFilteredDivisions(int countryId){
        DivisionDao divisionDao = new DivisionDaoImpl();
        return divisionDao.getDivisionsByCountry(countryId);
    }
}
