package com.booking.service;

import com.booking.dao.BookingDAO;
import com.booking.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BookingService {

    private static final Logger logger =
            LoggerFactory.getLogger(BookingService.class);

    private final BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new BookingDAO();
    }

    // ================= CREATE =================

    public Long createBooking(Booking booking) {

        if (booking == null) {
            logger.error("Booking cannot be null");
            return null;
        }

        logger.info("Creating booking for user ID: {}",
                booking.getUserId());

        Long bookingId = bookingDAO.createBooking(booking);

        if (bookingId != null) {
            logger.info("Booking created successfully with ID: {}",
                    bookingId);
        } else {
            logger.error("Failed to create booking");
        }

        return bookingId;
    }

    // ================= READ BY ID =================

    public Booking getBookingById(Long bookingId) {

        if (bookingId == null || bookingId <= 0) {
            logger.error("Invalid booking ID: {}", bookingId);
            return null;
        }

        logger.info("Fetching booking with ID: {}", bookingId);

        Booking booking = bookingDAO.getBookingById(bookingId);

        if (booking != null) {
            logger.info("Booking found with ID: {}", bookingId);
        } else {
            logger.warn("Booking not found with ID: {}", bookingId);
        }

        return booking;
    }

    // ================= READ ALL =================

    public List<Booking> getAllBookings() {

        logger.info("Fetching all bookings");

        List<Booking> bookings = bookingDAO.getAllBookings();

        logger.info("Total bookings fetched: {}", bookings.size());

        return bookings;
    }

    // ================= UPDATE =================

    public boolean updateBooking(Booking booking) {

        if (booking == null || booking.getBookingId() == null) {
            logger.error("Invalid booking for update");
            return false;
        }

        logger.info("Updating booking with ID: {}",
                booking.getBookingId());

        boolean updated = bookingDAO.updateBooking(booking);

        if (updated) {
            logger.info("Booking updated successfully with ID: {}",
                    booking.getBookingId());
        } else {
            logger.warn("Booking update failed for ID: {}",
                    booking.getBookingId());
        }

        return updated;
    }

    // ================= DELETE =================

    public boolean deleteBooking(Long bookingId) {

        if (bookingId == null || bookingId <= 0) {
            logger.error("Invalid booking ID: {}", bookingId);
            return false;
        }

        logger.info("Deleting booking with ID: {}", bookingId);

        boolean deleted = bookingDAO.deleteBooking(bookingId);

        if (deleted) {
            logger.info("Booking deleted successfully with ID: {}",
                    bookingId);
        } else {
            logger.warn("Booking deletion failed for ID: {}",
                    bookingId);
        }

        return deleted;
    }
}