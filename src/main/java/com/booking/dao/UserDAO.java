package com.booking.dao;

import com.booking.DBConnection;
import com.booking.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO {
    private static final Logger logger =
            Logger.getLogger(UserDAO.class.getName());

    private static final String INSERT_USER =
            "INSERT INTO `user` " + "(full_name, email, password_hash, phone, role, status) " + "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID =
            "SELECT * FROM `user` WHERE user_id = ?";

    private static final String SELECT_ALL_USERS =
            "SELECT * FROM `user`";

    private static final String UPDATE_USER =
            "UPDATE `user` SET " + "full_name = ?, email = ?, password_hash = ?, " + "phone = ?, role = ?, status = ? " + "WHERE user_id = ?";

    private static final String DELETE_USER =
            "DELETE FROM `user` WHERE user_id = ?";

    // CREATE
    public void addUser(User user) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER)) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getRole());
            stmt.setString(6, user.getStatus());

            stmt.executeUpdate();

            logger.info("User added successfully");

        } catch (SQLException e) {
            logger.severe("Error adding user: " + e.getMessage());
        }
    }

    // READ - Get user by ID
    public User getUserById(long userId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_ID)) {

            stmt.setLong(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    User user = new User();

                    user.setUserId(rs.getLong("user_id"));
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPasswordHash(rs.getString("password_hash"));
                    user.setPhone(rs.getString("phone"));
                    user.setRole(rs.getString("role"));
                    user.setStatus(rs.getString("status"));

                    return user;
                }
            }

        } catch (SQLException e) {
            logger.severe("Error getting user by ID: " + e.getMessage());
        }

        return null;
    }

    // READ - Get all users
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                User user = new User();

                user.setUserId(rs.getLong("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));

                users.add(user);
            }

        } catch (SQLException e) {
            logger.severe("Error getting all users: " + e.getMessage());
        }

        return users;
    }

    // UPDATE
    public void updateUser(User user) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_USER)) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getRole());
            stmt.setString(6, user.getStatus());
            stmt.setLong(7, user.getUserId());

            stmt.executeUpdate();

            logger.info("User updated successfully");

        } catch (SQLException e) {
            logger.severe("Error updating user: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteUser(long userId) {


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_USER)) {

            stmt.setLong(1, userId);

            stmt.executeUpdate();

            logger.info("User deleted successfully");

        } catch (SQLException e) {
            logger.severe("Error deleting user: " + e.getMessage());
        }
    }
}