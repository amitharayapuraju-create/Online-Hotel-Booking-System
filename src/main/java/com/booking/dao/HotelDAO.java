package com.booking.dao;

import com.booking.DBConnection;
import com.booking.model.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HotelDAO {

    private static final Logger logger =
            Logger.getLogger(HotelDAO.class.getName());

    // ================= CREATE =================

    private static final String INSERT_HOTEL =
            "INSERT INTO hotel " +
                    "(location_id, name, description, address, star_rating, amenities, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    // ================= READ BY ID =================

    private static final String SELECT_HOTEL_BY_ID =
            "SELECT hotel_id, location_id, name, description, address, " +
                    "star_rating, amenities, status, created_at, updated_at " +
                    "FROM hotel WHERE hotel_id = ?";

    // ================= READ ALL =================

    private static final String SELECT_ALL_HOTELS =
            "SELECT hotel_id, location_id, name, description, address, " +
                    "star_rating, amenities, status, created_at, updated_at " +
                    "FROM hotel ORDER BY hotel_id";

    // ================= UPDATE =================

    private static final String UPDATE_HOTEL =
            "UPDATE hotel SET " +
                    "location_id = ?, name = ?, description = ?, address = ?, " +
                    "star_rating = ?, amenities = ?, status = ? " +
                    "WHERE hotel_id = ?";

    // ================= DELETE =================

    private static final String DELETE_HOTEL =
            "DELETE FROM hotel WHERE hotel_id = ?";


    // =========================================================
    // CREATE
    // =========================================================

    public Long createHotel(Hotel hotel) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     INSERT_HOTEL,
                     Statement.RETURN_GENERATED_KEYS)) {

            if (hotel.getLocationId() != null) {
                stmt.setLong(1, hotel.getLocationId());
            } else {
                stmt.setNull(1, Types.BIGINT);
            }

            stmt.setString(2, hotel.getName());
            stmt.setString(3, hotel.getDescription());
            stmt.setString(4, hotel.getAddress());

            if (hotel.getStarRating() != null) {
                stmt.setBigDecimal(5, hotel.getStarRating());
            } else {
                stmt.setNull(5, Types.DECIMAL);
            }

            stmt.setString(6, hotel.getAmenities());
            stmt.setString(7, hotel.getStatus());

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    if (rs.next()) {

                        Long generatedId = rs.getLong(1);

                        hotel.setHotelId(generatedId);

                        logger.info(
                                "Hotel created successfully with ID: "
                                        + generatedId
                        );

                        return generatedId;
                    }
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error creating hotel: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // READ BY ID
    // =========================================================

    public Hotel getHotelById(Long hotelId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_HOTEL_BY_ID)) {

            stmt.setLong(1, hotelId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Hotel hotel = mapResultSetToHotel(rs);

                    logger.info(
                            "Hotel fetched successfully with ID: "
                                    + hotelId
                    );

                    return hotel;
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching hotel: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // READ ALL
    // =========================================================

    public List<Hotel> getAllHotels() {

        List<Hotel> hotels = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_ALL_HOTELS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Hotel hotel = mapResultSetToHotel(rs);

                hotels.add(hotel);
            }

            logger.info(
                    "Hotels fetched successfully. Total: "
                            + hotels.size()
            );

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching hotels: " + e.getMessage()
            );
        }

        return hotels;
    }


    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updateHotel(Hotel hotel) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(UPDATE_HOTEL)) {

            if (hotel.getLocationId() != null) {
                stmt.setLong(1, hotel.getLocationId());
            } else {
                stmt.setNull(1, Types.BIGINT);
            }

            stmt.setString(2, hotel.getName());
            stmt.setString(3, hotel.getDescription());
            stmt.setString(4, hotel.getAddress());

            if (hotel.getStarRating() != null) {
                stmt.setBigDecimal(5, hotel.getStarRating());
            } else {
                stmt.setNull(5, Types.DECIMAL);
            }

            stmt.setString(6, hotel.getAmenities());
            stmt.setString(7, hotel.getStatus());

            stmt.setLong(8, hotel.getHotelId());

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                logger.info(
                        "Hotel updated successfully with ID: "
                                + hotel.getHotelId()
                );

                return true;
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error updating hotel: " + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // DELETE
    // =========================================================

    public boolean deleteHotel(Long hotelId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(DELETE_HOTEL)) {

            stmt.setLong(1, hotelId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                logger.info(
                        "Hotel deleted successfully with ID: "
                                + hotelId
                );

                return true;
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error deleting hotel: " + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // RESULT SET → HOTEL OBJECT
    // =========================================================

    private Hotel mapResultSetToHotel(ResultSet rs)
            throws SQLException {

        Hotel hotel = new Hotel();

        hotel.setHotelId(
                rs.getLong("hotel_id")
        );

        long locationId =
                rs.getLong("location_id");

        if (rs.wasNull()) {
            hotel.setLocationId(null);
        } else {
            hotel.setLocationId(locationId);
        }

        hotel.setName(
                rs.getString("name")
        );

        hotel.setDescription(
                rs.getString("description")
        );

        hotel.setAddress(
                rs.getString("address")
        );

        hotel.setStarRating(
                rs.getBigDecimal("star_rating")
        );

        hotel.setAmenities(
                rs.getString("amenities")
        );

        hotel.setStatus(
                rs.getString("status")
        );

        Timestamp createdAt =
                rs.getTimestamp("created_at");

        if (createdAt != null) {
            hotel.setCreatedAt(
                    createdAt.toLocalDateTime()
            );
        }

        Timestamp updatedAt =
                rs.getTimestamp("updated_at");

        if (updatedAt != null) {
            hotel.setUpdatedAt(
                    updatedAt.toLocalDateTime()
            );
        }

        return hotel;
    }
}