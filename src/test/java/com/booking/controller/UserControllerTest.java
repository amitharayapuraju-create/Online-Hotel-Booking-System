package com.booking.controller;

public class UserControllerTest {

    public static void main(String[] args) {

        UserController userController =
                new UserController();

        // ================= GET ALL =================

        System.out.println("\n--- TEST GET ALL USERS ---");

        userController.getAllUsers();


        // ================= INVALID GET =================

        System.out.println("\n--- TEST GET USER WITH INVALID ID ---");

        userController.getUserById(-1L);


        // ================= INVALID ADD =================

        System.out.println("\n--- TEST ADD WITH NULL ---");

        userController.addUser(null);


        // ================= INVALID UPDATE =================

        System.out.println("\n--- TEST UPDATE WITH NULL ---");

        userController.updateUser(null);


        // ================= INVALID DELETE =================

        System.out.println("\n--- TEST DELETE WITH INVALID ID ---");

        userController.deleteUser(-1L);


        // ================= DONE =================

        System.out.println("\n==============================");
        System.out.println(" USER CONTROLLER TEST DONE");
        System.out.println("==============================");
    }
}