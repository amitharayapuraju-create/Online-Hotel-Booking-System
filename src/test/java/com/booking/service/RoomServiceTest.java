package com.booking.service;

import com.booking.model.Room;

import java.math.BigDecimal;
import java.util.List;

public class RoomServiceTest {

    public static void main(String[] args) {

        RoomService roomService = new RoomService();

        // =========================================================
        // CREATE ROOM
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("             CREATE ROOM");
        System.out.println("========================================");

        Room room = new Room();

        room.setHotelId(2L);
        room.setRoomNumber("102");
        room.setRoomType("DELUXE");
        room.setCapacity(2);
        room.setBasePrice(new BigDecimal("300.00"));
        room.setStatus("AVAILABLE");

        Long roomId = roomService.createRoom(room);

        System.out.println("Created Room ID: " + roomId);

        if (roomId == null) {
            System.out.println("Room creation failed.");
            return;
        }


        // =========================================================
        // READ ROOM BY ID
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("          READ ROOM BY ID");
        System.out.println("========================================");

        Room fetchedRoom =
                roomService.getRoomById(roomId);

        if (fetchedRoom != null) {

            System.out.println("Room fetched successfully.");
            System.out.println("Room ID: "
                    + fetchedRoom.getRoomId());
            System.out.println("Hotel ID: "
                    + fetchedRoom.getHotelId());
            System.out.println("Room Number: "
                    + fetchedRoom.getRoomNumber());
            System.out.println("Room Type: "
                    + fetchedRoom.getRoomType());
            System.out.println("Capacity: "
                    + fetchedRoom.getCapacity());
            System.out.println("Base Price: "
                    + fetchedRoom.getBasePrice());
            System.out.println("Status: "
                    + fetchedRoom.getStatus());

        } else {
            System.out.println("Room not found.");
        }


        // =========================================================
        // READ ALL ROOMS
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("             READ ALL ROOMS");
        System.out.println("========================================");

        List<Room> rooms =
                roomService.getAllRooms();

        System.out.println("Total Rooms: " + rooms.size());

        for (Room r : rooms) {

            System.out.println(
                    "ID: " + r.getRoomId()
                            + " | Hotel ID: " + r.getHotelId()
                            + " | Room Number: " + r.getRoomNumber()
                            + " | Type: " + r.getRoomType()
                            + " | Status: " + r.getStatus()
            );
        }


        // =========================================================
        // UPDATE ROOM
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("             UPDATE ROOM");
        System.out.println("========================================");

        room.setRoomId(roomId);
        room.setHotelId(2L);
        room.setRoomNumber("102");
        room.setRoomType("DELUXE");
        room.setCapacity(3);
        room.setBasePrice(new BigDecimal("350.00"));
        room.setStatus("AVAILABLE");

        boolean updated =
                roomService.updateRoom(room);

        System.out.println("Room updated: " + updated);


        // =========================================================
        // READ UPDATED ROOM
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("          READ UPDATED ROOM");
        System.out.println("========================================");

        Room updatedRoom =
                roomService.getRoomById(roomId);

        if (updatedRoom != null) {

            System.out.println("Updated Room ID: "
                    + updatedRoom.getRoomId());
            System.out.println("Updated Room Number: "
                    + updatedRoom.getRoomNumber());
            System.out.println("Updated Room Type: "
                    + updatedRoom.getRoomType());
            System.out.println("Updated Capacity: "
                    + updatedRoom.getCapacity());
            System.out.println("Updated Base Price: "
                    + updatedRoom.getBasePrice());
            System.out.println("Updated Status: "
                    + updatedRoom.getStatus());

        } else {
            System.out.println("Updated room not found.");
        }


        // =========================================================
        // DELETE ROOM
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("             DELETE ROOM");
        System.out.println("========================================");

        boolean deleted =
                roomService.deleteRoom(roomId);

        System.out.println("Room deleted: " + deleted);


        // =========================================================
        // TEST COMPLETE
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("          ROOM SERVICE TEST DONE");
        System.out.println("========================================");
    }
}