package com.booking;

import com.booking.dao.UserDAO;
import com.booking.model.User;

import java.util.List;

public class UserDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        // =====================================================
        // CREATE USER
        // =====================================================

        System.out.println("\n=================================");
        System.out.println("          CREATE USER");
        System.out.println("=================================");

        User user = new User();

        user.setFullName("DAO Test User");
        user.setEmail("dao_test_user@gmail.com");
        user.setPasswordHash("test_password_hash");
        user.setPhone("9876543210");
        user.setRole("CUSTOMER");
        user.setStatus("ACTIVE");

        userDAO.addUser(user);

        System.out.println("User added successfully.");


        // =====================================================
        // FIND CREATED USER ID
        // =====================================================

        List<User> users = userDAO.getAllUsers();

        long userId = -1;

        for (User u : users) {

            if ("dao_test_user@gmail.com".equals(u.getEmail())) {

                userId = u.getUserId();
                break;
            }
        }

        if (userId == -1) {

            System.out.println("Could not find newly created user.");
            return;
        }

        System.out.println("Created User ID: " + userId);


        // =====================================================
        // READ USER BY ID
        // =====================================================

        System.out.println("\n=================================");
        System.out.println("          READ USER");
        System.out.println("=================================");

        User fetchedUser = userDAO.getUserById(userId);

        if (fetchedUser != null) {

            System.out.println("User fetched successfully.");
            System.out.println("User ID: " + fetchedUser.getUserId());
            System.out.println("Full Name: " + fetchedUser.getFullName());
            System.out.println("Email: " + fetchedUser.getEmail());
            System.out.println("Phone: " + fetchedUser.getPhone());
            System.out.println("Role: " + fetchedUser.getRole());
            System.out.println("Status: " + fetchedUser.getStatus());

        } else {

            System.out.println("User could not be fetched.");
            return;
        }


        // =====================================================
        // READ ALL USERS
        // =====================================================

        System.out.println("\n=================================");
        System.out.println("          READ ALL USERS");
        System.out.println("=================================");

        List<User> allUsers = userDAO.getAllUsers();

        System.out.println("Total users: " + allUsers.size());

        for (User u : allUsers) {

            System.out.println(
                    "ID: " + u.getUserId()
                            + " | Name: " + u.getFullName()
                            + " | Email: " + u.getEmail()
                            + " | Role: " + u.getRole()
                            + " | Status: " + u.getStatus()
            );
        }


        // =====================================================
        // UPDATE USER
        // =====================================================

        System.out.println("\n=================================");
        System.out.println("          UPDATE USER");
        System.out.println("=================================");

        fetchedUser.setFullName("DAO Test User Updated");
        fetchedUser.setEmail("dao_test_user_updated@gmail.com");
        fetchedUser.setPasswordHash("updated_password_hash");
        fetchedUser.setPhone("9123456780");
        fetchedUser.setRole("CUSTOMER");
        fetchedUser.setStatus("ACTIVE");

        userDAO.updateUser(fetchedUser);

        System.out.println("User updated successfully.");


        // =====================================================
        // READ UPDATED USER
        // =====================================================

        System.out.println("\n=================================");
        System.out.println("       READ UPDATED USER");
        System.out.println("=================================");

        User updatedUser = userDAO.getUserById(userId);

        if (updatedUser != null) {

            System.out.println("User ID: " + updatedUser.getUserId());
            System.out.println("Full Name: " + updatedUser.getFullName());
            System.out.println("Email: " + updatedUser.getEmail());
            System.out.println("Phone: " + updatedUser.getPhone());
            System.out.println("Role: " + updatedUser.getRole());
            System.out.println("Status: " + updatedUser.getStatus());
        }


        // =====================================================
        // DELETE USER
        // =====================================================

        System.out.println("\n=================================");
        System.out.println("          DELETE USER");
        System.out.println("=================================");

        userDAO.deleteUser(userId);

        System.out.println("User deleted successfully.");


        // =====================================================
        // VERIFY DELETE
        // =====================================================

        System.out.println("\n=================================");
        System.out.println("       VERIFY DELETE");
        System.out.println("=================================");

        User deletedUser = userDAO.getUserById(userId);

        if (deletedUser == null) {

            System.out.println("User no longer exists.");
            System.out.println("Delete verified successfully.");

        } else {

            System.out.println("WARNING: User still exists.");
        }


        // =====================================================
        // FINAL MESSAGE
        // =====================================================

        System.out.println("\n=================================");
        System.out.println("       USER DAO CRUD TEST DONE");
        System.out.println("=================================");
    }
}