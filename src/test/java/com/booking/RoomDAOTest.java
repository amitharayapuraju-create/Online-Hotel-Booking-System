package com.booking;

import com.booking.dao.RoomDAO;
import com.booking.model.Room;

import java.math.BigDecimal;
import java.util.List;

public class RoomDAOTest {

    public static void main(String[] args) {

        RoomDAO roomDAO = new RoomDAO();

        // =====================================================
        // CREATE
        // =====================================================

        System.out.println("---- CREATE ROOM ----");

        Room room = new Room();

        // Use a hotel_id that already exists in your database.
        room.setHotelId(2L);
        room.setRoomNumber("101");
        room.setRoomType("DELUXE");
        room.setCapacity(2);
        room.setBasePrice(new BigDecimal("150.00"));
        room.setStatus("AVAILABLE");

        Long roomId = roomDAO.createRoom(room);

        System.out.println("Created Room ID: " + roomId);

        if (roomId == null) {
            System.out.println("Room creation failed.");
            return;
        }


        // =====================================================
        // READ BY ID
        // =====================================================

        System.out.println("\n---- READ ROOM ----");

        Room savedRoom = roomDAO.getRoomById(roomId);

        if (savedRoom != null) {

            System.out.println("ID: " + savedRoom.getRoomId());
            System.out.println("Hotel ID: " + savedRoom.getHotelId());
            System.out.println("Room Number: " + savedRoom.getRoomNumber());
            System.out.println("Room Type: " + savedRoom.getRoomType());
            System.out.println("Capacity: " + savedRoom.getCapacity());
            System.out.println("Base Price: " + savedRoom.getBasePrice());
            System.out.println("Status: " + savedRoom.getStatus());

        } else {

            System.out.println("Room not found.");
            return;
        }


        // =====================================================
        // READ ALL
        // =====================================================

        System.out.println("\n---- READ ALL ROOMS ----");

        List<Room> rooms = roomDAO.getAllRooms();

        for (Room r : rooms) {

            System.out.println(
                    r.getRoomId() + " | " +
                            r.getRoomNumber() + " | " +
                            r.getRoomType() + " | " +
                            r.getBasePrice() + " | " +
                            r.getStatus()
            );
        }


        // =====================================================
        // UPDATE
        // =====================================================

        System.out.println("\n---- UPDATE ROOM ----");

        savedRoom.setRoomNumber("101-UPDATED");
        savedRoom.setRoomType("SUITE");
        savedRoom.setCapacity(4);
        savedRoom.setBasePrice(new BigDecimal("250.00"));

        boolean updated = roomDAO.updateRoom(savedRoom);

        System.out.println("Room updated: " + updated);


        // =====================================================
        // READ UPDATED ROOM
        // =====================================================

        System.out.println("\n---- READ UPDATED ROOM ----");

        Room updatedRoom = roomDAO.getRoomById(roomId);

        if (updatedRoom != null) {

            System.out.println(
                    "Room Number: " +
                            updatedRoom.getRoomNumber()
            );

            System.out.println(
                    "Room Type: " +
                            updatedRoom.getRoomType()
            );

            System.out.println(
                    "Capacity: " +
                            updatedRoom.getCapacity()
            );

            System.out.println(
                    "Base Price: " +
                            updatedRoom.getBasePrice()
            );
        }


        // =====================================================
        // DELETE
        // =====================================================

        System.out.println("\n---- DELETE ROOM ----");

        boolean deleted = roomDAO.deleteRoom(roomId);

        System.out.println("Room deleted: " + deleted);


        // =====================================================
        // COMPLETE
        // =====================================================

        System.out.println("\nRoom DAO CRUD test completed!");
    }
}