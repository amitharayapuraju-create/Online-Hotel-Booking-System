package com.booking.test;

import com.booking.dao.UserDAO;
import com.booking.model.User;

public class UserDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        // READ
        System.out.println("---- READ USER ----");

        User user = userDAO.getUserById(1);

        if (user != null) {
            System.out.println("ID: " + user.getUserId());
            System.out.println("Name: " + user.getFullName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhone());
            System.out.println("Role: " + user.getRole());
            System.out.println("Status: " + user.getStatus());
        } else {
            System.out.println("User not found.");
        }


        // UPDATE
        System.out.println("\n---- UPDATE USER ----");

        user.setFullName("Updated Test User");
        user.setPhone("9999999999");

        userDAO.updateUser(user);


        // READ AGAIN
        System.out.println("\n---- READ UPDATED USER ----");

        User updatedUser = userDAO.getUserById(1);

        if (updatedUser != null) {
            System.out.println("Name: " + updatedUser.getFullName());
            System.out.println("Phone: " + updatedUser.getPhone());
        }


        // DELETE
        System.out.println("\n---- DELETE USER ----");

        userDAO.deleteUser(1);

        System.out.println("CRUD test completed!");
    }
}