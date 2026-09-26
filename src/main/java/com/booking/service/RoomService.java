package com.booking.service;

import com.booking.dao.RoomDAO;
import com.booking.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoomService {

    private static final Logger logger =
            LoggerFactory.getLogger(RoomService.class);

    private final RoomDAO roomDAO;

    public RoomService() {
        this.roomDAO = new RoomDAO();
    }

    // ================= CREATE =================

    public Long createRoom(Room room) {

        if (room == null) {
            logger.error("Room cannot be null");
            return null;
        }

        logger.info("Creating room: {}", room.getRoomNumber());

        Long roomId = roomDAO.createRoom(room);

        if (roomId != null) {
            logger.info("Room created successfully with ID: {}", roomId);
        } else {
            logger.error("Failed to create room");
        }

        return roomId;
    }

    // ================= READ BY ID =================

    public Room getRoomById(Long roomId) {

        if (roomId == null || roomId <= 0) {
            logger.error("Invalid room ID: {}", roomId);
            return null;
        }

        logger.info("Fetching room with ID: {}", roomId);

        Room room = roomDAO.getRoomById(roomId);

        if (room != null) {
            logger.info("Room found with ID: {}", roomId);
        } else {
            logger.warn("Room not found with ID: {}", roomId);
        }

        return room;
    }

    // ================= READ ALL =================

    public List<Room> getAllRooms() {

        logger.info("Fetching all rooms");

        List<Room> rooms = roomDAO.getAllRooms();

        logger.info("Total rooms fetched: {}", rooms.size());

        return rooms;
    }

    // ================= UPDATE =================

    public boolean updateRoom(Room room) {

        if (room == null || room.getRoomId() == null) {
            logger.error("Invalid room for update");
            return false;
        }

        logger.info("Updating room with ID: {}",
                room.getRoomId());

        boolean updated = roomDAO.updateRoom(room);

        if (updated) {
            logger.info("Room updated successfully with ID: {}",
                    room.getRoomId());
        } else {
            logger.warn("Room update failed for ID: {}",
                    room.getRoomId());
        }

        return updated;
    }

    // ================= DELETE =================

    public boolean deleteRoom(Long roomId) {

        if (roomId == null || roomId <= 0) {
            logger.error("Invalid room ID: {}", roomId);
            return false;
        }

        logger.info("Deleting room with ID: {}", roomId);

        boolean deleted = roomDAO.deleteRoom(roomId);

        if (deleted) {
            logger.info("Room deleted successfully with ID: {}",
                    roomId);
        } else {
            logger.warn("Room deletion failed for ID: {}",
                    roomId);
        }

        return deleted;
    }
}