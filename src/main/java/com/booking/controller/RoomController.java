package com.booking.controller;

import com.booking.model.Room;
import com.booking.service.RoomService;

import java.util.List;

public class RoomController {

    private final RoomService roomService;

    public RoomController() {
        this.roomService = new RoomService();
    }

    // ================= CREATE =================

    public Long createRoom(Room room) {

        if (room == null) {
            System.out.println("Invalid room");
            return null;
        }

        System.out.println("Creating room...");

        Long roomId = roomService.createRoom(room);

        if (roomId != null) {
            System.out.println("Room created successfully");
            System.out.println("Room ID: " + roomId);
        } else {
            System.out.println("Room creation failed");
        }

        return roomId;
    }

    // ================= READ BY ID =================

    public Room getRoomById(Long roomId) {

        if (roomId == null || roomId <= 0) {
            System.out.println("Invalid room ID");
            return null;
        }

        System.out.println(
                "Fetching room with ID: " + roomId);

        Room room = roomService.getRoomById(roomId);

        if (room != null) {
            System.out.println("Room found");
            System.out.println(
                    "Room ID: " + room.getRoomId());
            System.out.println(
                    "Room Number: " + room.getRoomNumber());
            System.out.println(
                    "Room Type: " + room.getRoomType());
        } else {
            System.out.println("Room not found");
        }

        return room;
    }

    // ================= READ ALL =================

    public List<Room> getAllRooms() {

        System.out.println("Fetching all rooms...");

        List<Room> rooms = roomService.getAllRooms();

        System.out.println(
                "Total rooms: " + rooms.size());

        return rooms;
    }

    // ================= UPDATE =================

    public boolean updateRoom(Room room) {

        if (room == null ||
                room.getRoomId() == null ||
                room.getRoomId() <= 0) {

            System.out.println("Invalid room for update");
            return false;
        }

        System.out.println(
                "Updating room with ID: "
                        + room.getRoomId());

        boolean updated = roomService.updateRoom(room);

        if (updated) {
            System.out.println("Room updated successfully");
        } else {
            System.out.println("Room update failed");
        }

        return updated;
    }

    // ================= DELETE =================

    public boolean deleteRoom(Long roomId) {

        if (roomId == null || roomId <= 0) {
            System.out.println("Invalid room ID");
            return false;
        }

        System.out.println(
                "Deleting room with ID: " + roomId);

        boolean deleted = roomService.deleteRoom(roomId);

        if (deleted) {
            System.out.println("Room deleted successfully");
        } else {
            System.out.println("Room deletion failed");
        }

        return deleted;
    }
}