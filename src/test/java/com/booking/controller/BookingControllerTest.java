package com.booking.controller;

import com.booking.model.Booking;

public class BookingControllerTest {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   BOOKING CONTROLLER TEST");
        System.out.println("=================================");

        BookingController bookingController =
                new BookingController();

        // ================= INVALID CREATE =================

        System.out.println("\n--- TEST CREATE WITH NULL ---");

        Long result = bookingController.createBooking(null);

        System.out.println("Result: " + result);

        // ================= INVALID READ =================

        System.out.println("\n--- TEST GET BOOKING WITH INVALID ID ---");

        Booking booking =
                bookingController.getBookingById(-1L);

        System.out.println("Result: " + booking);

        // ================= READ ALL =================

        System.out.println("\n--- TEST GET ALL BOOKINGS ---");

        bookingController.getAllBookings();

        // ================= INVALID UPDATE =================

        System.out.println("\n--- TEST UPDATE WITH NULL ---");

        boolean updated =
                bookingController.updateBooking(null);

        System.out.println("Updated: " + updated);

        // ================= INVALID DELETE =================

        System.out.println("\n--- TEST DELETE WITH INVALID ID ---");

        boolean deleted =
                bookingController.deleteBooking(-1L);

        System.out.println("Deleted: " + deleted);

        // ================= COMPLETE =================

        System.out.println("\n=================================");
        System.out.println(" BOOKING CONTROLLER TEST DONE");
        System.out.println("=================================");
    }
}