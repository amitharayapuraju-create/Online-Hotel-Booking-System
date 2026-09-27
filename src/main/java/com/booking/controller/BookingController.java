package com.booking.controller;

import com.booking.model.Booking;
import com.booking.service.BookingService;

import java.util.List;

public class BookingController {

    private final BookingService bookingService;

    public BookingController() {
        this.bookingService = new BookingService();
    }

    // ================= CREATE =================

    public Long createBooking(Booking booking) {

        if (booking == null) {
            System.out.println("Booking cannot be null");
            return null;
        }

        System.out.println("Creating booking...");

        Long bookingId = bookingService.createBooking(booking);

        if (bookingId != null) {
            System.out.println("Booking created successfully with ID: " + bookingId);
        } else {
            System.out.println("Failed to create booking");
        }

        return bookingId;
    }

    // ================= READ BY ID =================

    public Booking getBookingById(Long bookingId) {

        if (bookingId == null || bookingId <= 0) {
            System.out.println("Invalid booking ID");
            return null;
        }

        System.out.println("Fetching booking with ID: " + bookingId);

        Booking booking = bookingService.getBookingById(bookingId);

        if (booking != null) {
            System.out.println("Booking found");
        } else {
            System.out.println("Booking not found");
        }

        return booking;
    }

    // ================= READ ALL =================

    public List<Booking> getAllBookings() {

        System.out.println("Fetching all bookings...");

        List<Booking> bookings = bookingService.getAllBookings();

        System.out.println("Total bookings: " + bookings.size());

        return bookings;
    }

    // ================= UPDATE =================

    public boolean updateBooking(Booking booking) {

        if (booking == null || booking.getBookingId() == null) {
            System.out.println("Invalid booking for update");
            return false;
        }

        System.out.println(
                "Updating booking with ID: " + booking.getBookingId());

        boolean updated = bookingService.updateBooking(booking);

        if (updated) {
            System.out.println("Booking updated successfully");
        } else {
            System.out.println("Booking update failed");
        }

        return updated;
    }

    // ================= DELETE =================

    public boolean deleteBooking(Long bookingId) {

        if (bookingId == null || bookingId <= 0) {
            System.out.println("Invalid booking ID");
            return false;
        }

        System.out.println("Deleting booking with ID: " + bookingId);

        boolean deleted = bookingService.deleteBooking(bookingId);

        if (deleted) {
            System.out.println("Booking deleted successfully");
        } else {
            System.out.println("Booking deletion failed");
        }

        return deleted;
    }
}