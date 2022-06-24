package model;

/**
 * This class is for Month &amp; Type Reports.
 *
 * @author Lydia Strough
 */
public class Report {
    /**
     * appointment month.
     * */
    private String month;
    /**
     * appointment type.
     * */
    private String type;
    /**
     * number of appointments, grouped by month and type.
     * */
    private int count;

    /**
     * This is the Month &amp; Type Report constructor.
     *
     * @param month appointment month
     * @param type appointment type
     * @param count number of appointments, grouped by month and type
     * */
    public Report(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month appointment month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type appointment type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count number of appointments to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * This is the Month &amp; Type Report toString method.
     *
     * This method provides default syntax for report information (converts hashcode to string, etc.).
     * */
    @Override
    public String toString() {
        return ("Report: " + month + " " + type + " " + Integer.toString(count));
    }
}