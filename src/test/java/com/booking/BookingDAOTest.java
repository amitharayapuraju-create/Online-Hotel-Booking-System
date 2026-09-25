package com.booking;

import com.booking.dao.BookingDAO;
import com.booking.model.Booking;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingDAOTest {

    public static void main(String[] args) {

        BookingDAO bookingDAO = new BookingDAO();

        // =====================================================
        // CREATE BOOKING
        // =====================================================

        System.out.println("=================================");
        System.out.println("       CREATE BOOKING");
        System.out.println("=================================");

        Booking booking = new Booking();

        // Existing IDs from your database
        booking.setUserId(3L);
        booking.setHotelId(2L);
        booking.setRoomId(4L);

        booking.setCheckInDate(LocalDate.of(2026, 10, 10));
        booking.setCheckOutDate(LocalDate.of(2026, 10, 12));

        booking.setGuests(2);
        booking.setTotalAmount(new BigDecimal("500.00"));
        booking.setBookingStatus("CONFIRMED");

        Long bookingId = bookingDAO.createBooking(booking);

        System.out.println("Created Booking ID: " + bookingId);

        if (bookingId == null) {
            System.out.println("Booking creation failed.");
            return;
        }


        // =====================================================
        // READ BOOKING
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       READ BOOKING");
        System.out.println("=================================");

        Booking savedBooking = bookingDAO.getBookingById(bookingId);

        if (savedBooking != null) {

            System.out.println("Booking ID: "
                    + savedBooking.getBookingId());

            System.out.println("User ID: "
                    + savedBooking.getUserId());

            System.out.println("Hotel ID: "
                    + savedBooking.getHotelId());

            System.out.println("Room ID: "
                    + savedBooking.getRoomId());

            System.out.println("Check-in Date: "
                    + savedBooking.getCheckInDate());

            System.out.println("Check-out Date: "
                    + savedBooking.getCheckOutDate());

            System.out.println("Guests: "
                    + savedBooking.getGuests());

            System.out.println("Total Amount: "
                    + savedBooking.getTotalAmount());

            System.out.println("Status: "
                    + savedBooking.getBookingStatus());

        } else {

            System.out.println("Booking could not be found.");
        }


        // =====================================================
        // READ ALL BOOKINGS
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       ALL BOOKINGS");
        System.out.println("=================================");

        bookingDAO.getAllBookings();


        // =====================================================
        // UPDATE BOOKING
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       UPDATE BOOKING");
        System.out.println("=================================");

        savedBooking.setGuests(3);
        savedBooking.setTotalAmount(new BigDecimal("750.00"));
        savedBooking.setBookingStatus("CONFIRMED");

        boolean updated = bookingDAO.updateBooking(savedBooking);

        System.out.println("Booking updated: " + updated);


        // =====================================================
        // READ UPDATED BOOKING
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       READ UPDATED BOOKING");
        System.out.println("=================================");

        Booking updatedBooking =
                bookingDAO.getBookingById(bookingId);

        if (updatedBooking != null) {

            System.out.println("Booking ID: "
                    + updatedBooking.getBookingId());

            System.out.println("Guests: "
                    + updatedBooking.getGuests());

            System.out.println("Total Amount: "
                    + updatedBooking.getTotalAmount());

            System.out.println("Status: "
                    + updatedBooking.getBookingStatus());
        }


        // =====================================================
        // DELETE BOOKING
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       DELETE BOOKING");
        System.out.println("=================================");

        boolean deleted =
                bookingDAO.deleteBooking(bookingId);

        System.out.println("Booking deleted: " + deleted);


        // =====================================================
        // TEST COMPLETE
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("   BOOKING DAO CRUD TEST DONE");
        System.out.println("=================================");
    }
}