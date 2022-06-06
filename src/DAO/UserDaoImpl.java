package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

public class UserDaoImpl implements UserDao{                                                  //CRUD (implements UserDao)
    ObservableList<User> users = FXCollections.observableArrayList();

    public ObservableList<User> getAllUsers(){
        return users;
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public void updateUser(int index, User newUser) {
        users.set(index, newUser);
    }

    public boolean deleteUser(User selectedUser) {
        return users.remove(selectedUser);
    }
}
