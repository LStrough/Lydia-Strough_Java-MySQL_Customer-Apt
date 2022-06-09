package DAO;

import javafx.collections.ObservableList;
import model.User;

public interface UserDao {
    public ObservableList<User> getAllUsers();
    public User getUser(int userId);
    public void updateUser(int index, User newUser);
    public boolean deleteUser(User selectedUser);
    public void addUser(User user);
    public void display();                                          //call with Malcolm
}
