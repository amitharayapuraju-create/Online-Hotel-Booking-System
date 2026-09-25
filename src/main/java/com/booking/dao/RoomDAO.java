package com.booking.dao;

import com.booking.DBConnection;
import com.booking.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RoomDAO {

    private static final Logger logger =
            Logger.getLogger(RoomDAO.class.getName());

    // ================= CREATE =================

    private static final String INSERT_ROOM =
            "INSERT INTO room " +
                    "(hotel_id, room_number, room_type, capacity, base_price, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    // ================= READ BY ID =================

    private static final String SELECT_ROOM_BY_ID =
            "SELECT room_id, hotel_id, room_number, room_type, " +
                    "capacity, base_price, status, created_at, updated_at " +
                    "FROM room WHERE room_id = ?";

    // ================= READ ALL =================

    private static final String SELECT_ALL_ROOMS =
            "SELECT room_id, hotel_id, room_number, room_type, " +
                    "capacity, base_price, status, created_at, updated_at " +
                    "FROM room ORDER BY room_id";

    // ================= UPDATE =================

    private static final String UPDATE_ROOM =
            "UPDATE room SET " +
                    "hotel_id = ?, room_number = ?, room_type = ?, " +
                    "capacity = ?, base_price = ?, status = ? " +
                    "WHERE room_id = ?";

    // ================= DELETE =================

    private static final String DELETE_ROOM =
            "DELETE FROM room WHERE room_id = ?";


    // =========================================================
    // CREATE ROOM
    // =========================================================

    public Long createRoom(Room room) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     INSERT_ROOM,
                     Statement.RETURN_GENERATED_KEYS)) {

            if (room.getHotelId() != null) {
                stmt.setLong(1, room.getHotelId());
            } else {
                stmt.setNull(1, Types.BIGINT);
            }

            stmt.setString(2, room.getRoomNumber());
            stmt.setString(3, room.getRoomType());

            if (room.getCapacity() != null) {
                stmt.setInt(4, room.getCapacity());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            if (room.getBasePrice() != null) {
                stmt.setBigDecimal(5, room.getBasePrice());
            } else {
                stmt.setNull(5, Types.DECIMAL);
            }

            stmt.setString(6, room.getStatus());

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    if (rs.next()) {

                        Long generatedId = rs.getLong(1);

                        room.setRoomId(generatedId);

                        logger.info(
                                "Room created successfully with ID: "
                                        + generatedId
                        );

                        return generatedId;
                    }
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error creating room: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // READ ROOM BY ID
    // =========================================================

    public Room getRoomById(Long roomId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_ROOM_BY_ID)) {

            stmt.setLong(1, roomId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Room room = mapResultSetToRoom(rs);

                    logger.info(
                            "Room fetched successfully with ID: "
                                    + roomId
                    );

                    return room;
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching room: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // READ ALL ROOMS
    // =========================================================

    public List<Room> getAllRooms() {

        List<Room> rooms = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_ALL_ROOMS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Room room = mapResultSetToRoom(rs);

                rooms.add(room);
            }

            logger.info(
                    "Rooms fetched successfully. Total: "
                            + rooms.size()
            );

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching rooms: " + e.getMessage()
            );
        }

        return rooms;
    }


    // =========================================================
    // UPDATE ROOM
    // =========================================================

    public boolean updateRoom(Room room) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(UPDATE_ROOM)) {

            if (room.getHotelId() != null) {
                stmt.setLong(1, room.getHotelId());
            } else {
                stmt.setNull(1, Types.BIGINT);
            }

            stmt.setString(2, room.getRoomNumber());
            stmt.setString(3, room.getRoomType());

            if (room.getCapacity() != null) {
                stmt.setInt(4, room.getCapacity());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            if (room.getBasePrice() != null) {
                stmt.setBigDecimal(5, room.getBasePrice());
            } else {
                stmt.setNull(5, Types.DECIMAL);
            }

            stmt.setString(6, room.getStatus());

            stmt.setLong(7, room.getRoomId());

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                logger.info(
                        "Room updated successfully with ID: "
                                + room.getRoomId()
                );

                return true;
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error updating room: " + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // DELETE ROOM
    // =========================================================

    public boolean deleteRoom(Long roomId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(DELETE_ROOM)) {

            stmt.setLong(1, roomId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                logger.info(
                        "Room deleted successfully with ID: "
                                + roomId
                );

                return true;
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error deleting room: " + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // RESULT SET → ROOM OBJECT
    // =========================================================

    private Room mapResultSetToRoom(ResultSet rs)
            throws SQLException {

        Room room = new Room();

        room.setRoomId(
                rs.getLong("room_id")
        );

        room.setHotelId(
                rs.getLong("hotel_id")
        );

        room.setRoomNumber(
                rs.getString("room_number")
        );

        room.setRoomType(
                rs.getString("room_type")
        );

        room.setCapacity(
                rs.getInt("capacity")
        );

        room.setBasePrice(
                rs.getBigDecimal("base_price")
        );

        room.setStatus(
                rs.getString("status")
        );

        Timestamp createdAt =
                rs.getTimestamp("created_at");

        if (createdAt != null) {
            room.setCreatedAt(
                    createdAt.toLocalDateTime()
            );
        }

        Timestamp updatedAt =
                rs.getTimestamp("updated_at");

        if (updatedAt != null) {
            room.setUpdatedAt(
                    updatedAt.toLocalDateTime()
            );
        }

        return room;
    }
}