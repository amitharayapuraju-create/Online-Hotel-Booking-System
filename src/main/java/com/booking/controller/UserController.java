package com.booking.controller;

import com.booking.model.User;
import com.booking.service.UserService;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    // ================= CREATE =================

    public void addUser(User user) {

        if (user == null) {
            System.out.println("Invalid user");
            return;
        }

        System.out.println("Adding user...");

        userService.addUser(user);

        System.out.println("User add operation completed");
    }

    // ================= READ BY ID =================

    public User getUserById(Long userId) {

        if (userId == null || userId <= 0) {
            System.out.println("Invalid user ID");
            return null;
        }

        System.out.println(
                "Fetching user with ID: " + userId
        );

        User user = userService.getUserById(userId);

        if (user != null) {
            System.out.println("User found successfully");
        } else {
            System.out.println("User not found");
        }

        return user;
    }

    // ================= READ ALL =================

    public List<User> getAllUsers() {

        System.out.println("Fetching all users...");

        List<User> users = userService.getAllUsers();

        System.out.println(
                "Total users fetched: " + users.size()
        );

        return users;
    }

    // ================= UPDATE =================

    public void updateUser(User user) {

        if (user == null || user.getUserId() == null) {
            System.out.println("Invalid user for update");
            return;
        }

        System.out.println(
                "Updating user with ID: " + user.getUserId()
        );

        userService.updateUser(user);

        System.out.println(
                "User update operation completed"
        );
    }

    // ================= DELETE =================

    public void deleteUser(Long userId) {

        if (userId == null || userId <= 0) {
            System.out.println("Invalid user ID");
            return;
        }

        System.out.println(
                "Deleting user with ID: " + userId
        );

        userService.deleteUser(userId);

        System.out.println(
                "User delete operation completed"
        );
    }
}