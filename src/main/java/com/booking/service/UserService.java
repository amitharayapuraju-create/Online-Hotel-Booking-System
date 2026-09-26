package com.booking.service;

import com.booking.dao.UserDAO;
import com.booking.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // ================= CREATE =================

    public void addUser(User user) {

        if (user == null) {
            logger.error("User cannot be null");
            return;
        }

        logger.info("Adding user with email: {}", user.getEmail());

        userDAO.addUser(user);

        logger.info("User add operation completed");
    }

    // ================= READ BY ID =================

    public User getUserById(Long userId) {

        if (userId == null || userId <= 0) {
            logger.error("Invalid user ID: {}", userId);
            return null;
        }

        logger.info("Fetching user with ID: {}", userId);

        User user = userDAO.getUserById(userId);

        if (user != null) {
            logger.info("User found with ID: {}", userId);
        } else {
            logger.warn("User not found with ID: {}", userId);
        }

        return user;
    }

    // ================= READ ALL =================

    public List<User> getAllUsers() {

        logger.info("Fetching all users");

        List<User> users = userDAO.getAllUsers();

        logger.info("Total users fetched: {}", users.size());

        return users;
    }

    // ================= UPDATE =================

    public void updateUser(User user) {

        if (user == null || user.getUserId() == null) {
            logger.error("Invalid user for update");
            return;
        }

        logger.info("Updating user with ID: {}",
                user.getUserId());

        userDAO.updateUser(user);

        logger.info("User update operation completed for ID: {}",
                user.getUserId());
    }

    // ================= DELETE =================

    public void deleteUser(Long userId) {

        if (userId == null || userId <= 0) {
            logger.error("Invalid user ID: {}", userId);
            return;
        }

        logger.info("Deleting user with ID: {}", userId);

        userDAO.deleteUser(userId);

        logger.info("User delete operation completed for ID: {}",
                userId);
    }
}