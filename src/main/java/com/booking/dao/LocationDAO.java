package com.booking.dao;

import com.booking.DBConnection;
import com.booking.model.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    // CREATE
    public Long createLocation(Location location) {
        String sql = """
                INSERT INTO location (name, type, parent_id)
                VALUES (?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

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
                        location.setLocationId(rs.getLong(1));
                        return generatedId;
                    }
                }
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ BY ID
    public Location getLocationById(Long locationId) {

        String sql = """
                SELECT location_id, name, type, parent_id,
                       created_at, updated_at
                FROM location
                WHERE location_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, locationId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Location location = new Location();

                    location.setLocationId(rs.getLong("location_id"));
                    location.setName(rs.getString("name"));
                    location.setType(rs.getString("type"));

                    long parentId = rs.getLong("parent_id");

                    if (rs.wasNull()) {
                        location.setParentId(null);
                    } else {
                        location.setParentId(parentId);
                    }

                    Timestamp createdAt = rs.getTimestamp("created_at");
                    Timestamp updatedAt = rs.getTimestamp("updated_at");

                    if (createdAt != null) {
                        location.setCreatedAt(
                                createdAt.toLocalDateTime());
                    }

                    if (updatedAt != null) {
                        location.setUpdatedAt(
                                updatedAt.toLocalDateTime());
                    }

                    return location;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // READ ALL
    public List<Location> getAllLocations() {

        List<Location> locations = new ArrayList<>();

        String sql = """
                SELECT location_id, name, type, parent_id,
                       created_at, updated_at
                FROM location
                ORDER BY location_id
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Location location = new Location();

                location.setLocationId(rs.getLong("location_id"));
                location.setName(rs.getString("name"));
                location.setType(rs.getString("type"));

                long parentId = rs.getLong("parent_id");

                if (rs.wasNull()) {
                    location.setParentId(null);
                } else {
                    location.setParentId(parentId);
                }

                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");

                if (createdAt != null) {
                    location.setCreatedAt(
                            createdAt.toLocalDateTime());
                }

                if (updatedAt != null) {
                    location.setUpdatedAt(
                            updatedAt.toLocalDateTime());
                }

                locations.add(location);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locations;
    }

    // UPDATE
    public boolean updateLocation(Location location) {

        String sql = """
                UPDATE location
                SET name = ?, type = ?, parent_id = ?
                WHERE location_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, location.getName());
            stmt.setString(2, location.getType());

            if (location.getParentId() != null) {
                stmt.setLong(3, location.getParentId());
            } else {
                stmt.setNull(3, Types.BIGINT);
            }

            stmt.setLong(4, location.getLocationId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // DELETE
    public boolean deleteLocation(Long locationId) {

        String sql = """
                DELETE FROM location
                WHERE location_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, locationId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}