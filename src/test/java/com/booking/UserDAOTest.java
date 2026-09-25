package com.booking;

import com.booking.dao.UserDAO;
import com.booking.model.User;

public class UserDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        // Use ONE user ID throughout the test
        long userId = 2L;

        // =========================
        // READ
        // =========================
        System.out.println("---- READ USER ----");

        User user = userDAO.getUserById(userId);

        if (user != null) {
            System.out.println("ID: " + user.getUserId());
            System.out.println("Name: " + user.getFullName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhone());
            System.out.println("Role: " + user.getRole());
            System.out.println("Status: " + user.getStatus());

            // =========================
            // UPDATE
            // =========================
            System.out.println("\n---- UPDATE USER ----");

            user.setFullName("Updated Test User");
            user.setPhone("9999999999");

            userDAO.updateUser(user);

            // =========================
            // READ AGAIN
            // =========================
            System.out.println("\n---- READ UPDATED USER ----");

            User updatedUser = userDAO.getUserById(userId);

            if (updatedUser != null) {
                System.out.println("Name: " + updatedUser.getFullName());
                System.out.println("Phone: " + updatedUser.getPhone());
            }

            // =========================
            // DELETE
            // =========================
            System.out.println("\n---- DELETE USER ----");

            userDAO.deleteUser(userId);

            System.out.println("CRUD test completed!");

        } else {
            System.out.println("User not found. Test stopped.");
        }
    }
}