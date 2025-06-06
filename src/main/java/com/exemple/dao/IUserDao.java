package com.exemple.dao;

import com.exemple.model.User;

import java.util.List;

public interface IUserDao {
    void save(User u);
    User verifyUser(String login, String password);
    boolean isEmailExists(String email);
    List<User> getAllUsers();
    List<User> searchUsersByEmail(String emailFilter);
    void deleteByEmail(String email);
    void updateUserRole(String email, String newRole);

}
