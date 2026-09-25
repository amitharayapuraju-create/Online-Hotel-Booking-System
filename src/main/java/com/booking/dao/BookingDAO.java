package com.booking.dao;

import com.booking.DBConnection;
import com.booking.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // =========================================================
    // CREATE BOOKING
    // =========================================================

    public Long createBooking(Booking booking) {

        String sql = """
                INSERT INTO booking
                (user_id, hotel_id, room_id, check_in_date, check_out_date,
                 guests, total_amount, booking_status)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, booking.getUserId());
            stmt.setLong(2, booking.getHotelId());
            stmt.setLong(3, booking.getRoomId());
            stmt.setDate(4, Date.valueOf(booking.getCheckInDate()));
            stmt.setDate(5, Date.valueOf(booking.getCheckOutDate()));
            stmt.setInt(6, booking.getGuests());
            stmt.setBigDecimal(7, booking.getTotalAmount());
            stmt.setString(8, booking.getBookingStatus());

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    if (rs.next()) {

                        Long bookingId = rs.getLong(1);

                        System.out.println(
                                "Booking created successfully with ID: "
                                        + bookingId
                        );

                        return bookingId;
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error creating booking: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // READ BOOKING BY ID
    // =========================================================

    public Booking getBookingById(Long bookingId) {

        String sql = """
                SELECT booking_id, user_id, hotel_id, room_id,
                       check_in_date, check_out_date, guests,
                       total_amount, booking_status,
                       created_at, updated_at
                FROM booking
                WHERE booking_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, bookingId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Booking booking = new Booking();

                    booking.setBookingId(rs.getLong("booking_id"));
                    booking.setUserId(rs.getLong("user_id"));
                    booking.setHotelId(rs.getLong("hotel_id"));
                    booking.setRoomId(rs.getLong("room_id"));

                    Date checkIn = rs.getDate("check_in_date");
                    Date checkOut = rs.getDate("check_out_date");

                    if (checkIn != null) {
                        booking.setCheckInDate(checkIn.toLocalDate());
                    }

                    if (checkOut != null) {
                        booking.setCheckOutDate(checkOut.toLocalDate());
                    }

                    booking.setGuests(rs.getInt("guests"));
                    booking.setTotalAmount(rs.getBigDecimal("total_amount"));
                    booking.setBookingStatus(rs.getString("booking_status"));

                    Timestamp createdAt = rs.getTimestamp("created_at");
                    Timestamp updatedAt = rs.getTimestamp("updated_at");

                    if (createdAt != null) {
                        booking.setCreatedAt(createdAt.toLocalDateTime());
                    }

                    if (updatedAt != null) {
                        booking.setUpdatedAt(updatedAt.toLocalDateTime());
                    }

                    System.out.println(
                            "Booking fetched successfully with ID: "
                                    + bookingId
                    );

                    return booking;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error getting booking: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // READ ALL BOOKINGS
    // =========================================================

    public List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        String sql = """
                SELECT booking_id, user_id, hotel_id, room_id,
                       check_in_date, check_out_date, guests,
                       total_amount, booking_status,
                       created_at, updated_at
                FROM booking
                ORDER BY booking_id
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Booking booking = new Booking();

                booking.setBookingId(rs.getLong("booking_id"));
                booking.setUserId(rs.getLong("user_id"));
                booking.setHotelId(rs.getLong("hotel_id"));
                booking.setRoomId(rs.getLong("room_id"));

                Date checkIn = rs.getDate("check_in_date");
                Date checkOut = rs.getDate("check_out_date");

                if (checkIn != null) {
                    booking.setCheckInDate(checkIn.toLocalDate());
                }

                if (checkOut != null) {
                    booking.setCheckOutDate(checkOut.toLocalDate());
                }

                booking.setGuests(rs.getInt("guests"));
                booking.setTotalAmount(rs.getBigDecimal("total_amount"));
                booking.setBookingStatus(rs.getString("booking_status"));

                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");

                if (createdAt != null) {
                    booking.setCreatedAt(createdAt.toLocalDateTime());
                }

                if (updatedAt != null) {
                    booking.setUpdatedAt(updatedAt.toLocalDateTime());
                }

                bookings.add(booking);
            }

            System.out.println(
                    "Total bookings fetched: " + bookings.size()
            );

        } catch (SQLException e) {

            System.out.println(
                    "Error getting all bookings: " + e.getMessage()
            );
        }

        return bookings;
    }


    // =========================================================
    // UPDATE BOOKING
    // =========================================================

    public boolean updateBooking(Booking booking) {

        String sql = """
                UPDATE booking
                SET user_id = ?,
                    hotel_id = ?,
                    room_id = ?,
                    check_in_date = ?,
                    check_out_date = ?,
                    guests = ?,
                    total_amount = ?,
                    booking_status = ?
                WHERE booking_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, booking.getUserId());
            stmt.setLong(2, booking.getHotelId());
            stmt.setLong(3, booking.getRoomId());
            stmt.setDate(4, Date.valueOf(booking.getCheckInDate()));
            stmt.setDate(5, Date.valueOf(booking.getCheckOutDate()));
            stmt.setInt(6, booking.getGuests());
            stmt.setBigDecimal(7, booking.getTotalAmount());
            stmt.setString(8, booking.getBookingStatus());
            stmt.setLong(9, booking.getBookingId());

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Booking updated successfully with ID: "
                                + booking.getBookingId()
                );

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error updating booking: " + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // DELETE BOOKING
    // =========================================================

    public boolean deleteBooking(Long bookingId) {

        String sql = """
                DELETE FROM booking
                WHERE booking_id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, bookingId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Booking deleted successfully with ID: "
                                + bookingId
                );

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error deleting booking: " + e.getMessage()
            );
        }

        return false;
    }
}