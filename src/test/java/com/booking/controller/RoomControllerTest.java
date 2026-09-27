package com.booking.controller;

public class RoomControllerTest {

    public static void main(String[] args) {

        RoomController roomController =
                new RoomController();

        // ================= GET ALL =================

        System.out.println("\n--- TEST GET ALL ROOMS ---");

        roomController.getAllRooms();


        // ================= INVALID GET =================

        System.out.println("\n--- TEST GET ROOM WITH INVALID ID ---");

        roomController.getRoomById(-1L);


        // ================= INVALID UPDATE =================

        System.out.println("\n--- TEST UPDATE WITH NULL ---");

        boolean updated =
                roomController.updateRoom(null);

        System.out.println("Updated: " + updated);


        // ================= INVALID DELETE =================

        System.out.println("\n--- TEST DELETE WITH INVALID ID ---");

        boolean deleted =
                roomController.deleteRoom(-1L);

        System.out.println("Deleted: " + deleted);


        // ================= DONE =================

        System.out.println("\n==============================");
        System.out.println(" ROOM CONTROLLER TEST DONE");
        System.out.println("==============================");
    }
}