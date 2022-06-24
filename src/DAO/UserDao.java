package DAO;

import javafx.collections.ObservableList;
import model.User;

/**
 * This is the "User DAO" class.
 * This class acts as an Interface for the "User DAO Implementation" class.
 *
 * @author Lydia Strough
 */
public interface UserDao {

    /**
     * This is the "get All Users" method.
     * This method accesses the database and returns all users. Each user is then added to an observable list "allUsers".
     *
     * @return allUsers list
     */
    public ObservableList<User> getAllUsers();

    /**
     * This is the "get User" method.
     * This method searches the database for a specific user by their user ID.
     *
     * @param userId the user ID in question
     * @return the specific user information
     */
    public User getUser(int userId);

    /**
     * This is the "update User password" method.
     * This method searches the database for a specific user by their username and password, and then updates the password.
     *
     * @param userName database user username
     * @param newPassword new password
     * @param currentPassword database users' current password
     * @return the number of affected database rows
     */
    public int updateUserPass(String userName, String newPassword, String currentPassword);

    /**
     * This is the "update User username" method.
     * This method searches the database for a specific user by their username and password, and then updates the users' username.
     *
     * @param currentUserName database users' current username
     * @param newUserName database users' new desired username
     * @param password database users' password
     * @return the number of affected database rows
     */
    public int updateUserName(String currentUserName, String newUserName, String password);

    /**
     * This is the "delete User" method.
     * This method accesses the database and deletes a user with a specific user ID.
     *
     * @param userId the user ID in question
     * @return the number of affected database rows
     */
    public int deleteUser(int userId);

    /**
     * This is the "add User" method.
     * This method accesses the database and adds a user with the desired user name and password.
     *
     * @param userName the desired user name
     * @param password the desired password
     * @return the number of affected database rows
     */
    public int addUser(String userName, String password);
}
