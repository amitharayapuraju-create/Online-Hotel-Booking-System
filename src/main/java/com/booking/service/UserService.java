package com.booking.service;

import com.booking.dao.UserDAO;
import com.booking.exception.UserNotFoundException;
import com.booking.model.User;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // CREATE
    public void addUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (user.getFullName() == null || user.getFullName().isBlank()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        userDAO.addUser(user);
    }

    // READ
    public User getUserById(long userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        User user = userDAO.getUserById(userId);

        if (user == null) {
            throw new UserNotFoundException(
                    "User not found with ID: " + userId
            );
        }

        return user;
    }

    // UPDATE
    public void updateUser(User user) {

        if (user == null || user.getUserId() <= 0) {
            throw new IllegalArgumentException("Invalid user");
        }

        userDAO.updateUser(user);
    }

    // DELETE
    public void deleteUser(long userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        userDAO.deleteUser(userId);
    }
}