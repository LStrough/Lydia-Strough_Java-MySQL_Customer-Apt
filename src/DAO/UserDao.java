package DAO;

import javafx.collections.ObservableList;
import model.User;

public interface UserDao {
    public ObservableList<User> getAllUsers();
    public User getUser(int userId);
    public void updateUser(int index, User newUser);
    public boolean deleteUser(User selectedUser);
    public int addUser(String userName, String password);
}
