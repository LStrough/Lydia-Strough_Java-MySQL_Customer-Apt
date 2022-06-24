package model;

/**
 *This class is for database users.
 *
 * @author Lydia Strough
 */
public class User {
    /**
     * database user ID.
     * */
    private int userId;
    /**
     * database user name.
     * */
    private String userName;
    /**
     * database user password.
     */
    private String password;

    /**
     * This is the database user constructor.
     *
     * @param userId database user ID
     * @param userName database user name
     * @param password database user password
     * */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId database user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName database user name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password database user password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This is the database user toString method.
     *
     * This method provides default syntax for database user information (converts hashcode to string, etc.).
     * */
    @Override
    public String toString() {
        return ("[" + Integer.toString(userId) + "] " + userName);
    }
}
