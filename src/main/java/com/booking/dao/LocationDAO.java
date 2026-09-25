package com.booking.dao;

import com.booking.DBConnection;
import com.booking.model.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LocationDAO {

    // Logger
    private static final Logger logger =
            Logger.getLogger(LocationDAO.class.getName());

    // SQL Statements
    private static final String INSERT_LOCATION =
            "INSERT INTO location (name, type, parent_id) " +
                    "VALUES (?, ?, ?)";

    private static final String SELECT_LOCATION_BY_ID =
            "SELECT location_id, name, type, parent_id, " +
                    "created_at, updated_at " +
                    "FROM location " +
                    "WHERE location_id = ?";

    private static final String SELECT_ALL_LOCATIONS =
            "SELECT location_id, name, type, parent_id, " +
                    "created_at, updated_at " +
                    "FROM location " +
                    "ORDER BY location_id";

    private static final String UPDATE_LOCATION =
            "UPDATE location " +
                    "SET name = ?, type = ?, parent_id = ? " +
                    "WHERE location_id = ?";

    private static final String DELETE_LOCATION =
            "DELETE FROM location " +
                    "WHERE location_id = ?";


    // =========================
    // CREATE
    // =========================
    public Long createLocation(Location location) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     INSERT_LOCATION,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, location.getName());
            stmt.setString(2, location.getType());

            if (location.getParentId() != null) {
                stmt.setLong(3, location.getParentId());
            } else {
                stmt.setNull(3, Types.BIGINT);
            }

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    if (rs.next()) {

                        Long generatedId = rs.getLong(1);

                        location.setLocationId(generatedId);

                        logger.info("Location created successfully");

                        return generatedId;
                    }
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error creating location: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================
    // READ - GET BY ID
    // =========================
    public Location getLocationById(Long locationId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_LOCATION_BY_ID)) {

            stmt.setLong(1, locationId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Location location = new Location();

                    location.setLocationId(
                            rs.getLong("location_id")
                    );

                    location.setName(
                            rs.getString("name")
                    );

                    location.setType(
                            rs.getString("type")
                    );

                    long parentId =
                            rs.getLong("parent_id");

                    if (rs.wasNull()) {
                        location.setParentId(null);
                    } else {
                        location.setParentId(parentId);
                    }

                    Timestamp createdAt =
                            rs.getTimestamp("created_at");

                    Timestamp updatedAt =
                            rs.getTimestamp("updated_at");

                    if (createdAt != null) {
                        location.setCreatedAt(
                                createdAt.toLocalDateTime()
                        );
                    }

                    if (updatedAt != null) {
                        location.setUpdatedAt(
                                updatedAt.toLocalDateTime()
                        );
                    }

                    logger.info(
                            "Location fetched successfully"
                    );

                    return location;
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching location: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================
    // READ - GET ALL
    // =========================
    public List<Location> getAllLocations() {

        List<Location> locations = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_ALL_LOCATIONS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Location location = new Location();

                location.setLocationId(
                        rs.getLong("location_id")
                );

                location.setName(
                        rs.getString("name")
                );

                location.setType(
                        rs.getString("type")
                );

                long parentId =
                        rs.getLong("parent_id");

                if (rs.wasNull()) {
                    location.setParentId(null);
                } else {
                    location.setParentId(parentId);
                }

                Timestamp createdAt =
                        rs.getTimestamp("created_at");

                Timestamp updatedAt =
                        rs.getTimestamp("updated_at");

                if (createdAt != null) {
                    location.setCreatedAt(
                            createdAt.toLocalDateTime()
                    );
                }

                if (updatedAt != null) {
                    location.setUpdatedAt(
                            updatedAt.toLocalDateTime()
                    );
                }

                locations.add(location);
            }

            logger.info(
                    "All locations fetched successfully"
            );

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching locations: " + e.getMessage()
            );
        }

        return locations;
    }


    // =========================
    // UPDATE
    // =========================
    public boolean updateLocation(Location location) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(UPDATE_LOCATION)) {

            stmt.setString(1, location.getName());

            stmt.setString(2, location.getType());

            if (location.getParentId() != null) {
                stmt.setLong(3, location.getParentId());
            } else {
                stmt.setNull(3, Types.BIGINT);
            }

            stmt.setLong(
                    4,
                    location.getLocationId()
            );

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                logger.info(
                        "Location updated successfully"
                );

                return true;
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error updating location: " + e.getMessage()
            );
        }

        return false;
    }


    // =========================
    // DELETE
    // =========================
    public boolean deleteLocation(Long locationId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(DELETE_LOCATION)) {

            stmt.setLong(1, locationId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                logger.info(
                        "Location deleted successfully"
                );

                return true;
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error deleting location: " + e.getMessage()
            );
        }

        return false;
    }
}