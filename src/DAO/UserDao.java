package DAO;

import javafx.collections.ObservableList;
import model.User;

public interface UserDao {
    public ObservableList<User> getAllUsers();
    public User getUser(int userId);
    public int updateUserPass(String userName, String newPassword, String currentPassword);
    public int updateUserName(String currentUserName, String newUserName, String password);
    public int deleteUser(int userId);
    public int addUser(String userName, String password);
}
