package DAO;

import javafx.collections.ObservableList;
import model.User;

public class UserDaoImplv2 implements UserDao{

    @Override
    public ObservableList<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(int userId) {
        return null;
    }

    @Override
    public void updateUser(int index, User newUser) {

    }

    @Override
    public boolean deleteUser(User selectedUser) {
        return false;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void display() {
        System.out.println("User Dao Impl v2");
    }
}
