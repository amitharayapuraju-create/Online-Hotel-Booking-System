package com.booking;

import com.booking.controller.BookingController;
import com.booking.model.Booking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BookingController bookingController =
                new BookingController();

        System.out.println("=================================");
        System.out.println("       HOTEL BOOKING SYSTEM");
        System.out.println("=================================");

        // ================= USER ID =================

        System.out.print("Enter User ID: ");
        Long userId = scanner.nextLong();

        // ================= HOTEL ID =================

        System.out.print("Enter Hotel ID: ");
        Long hotelId = scanner.nextLong();

        // ================= ROOM ID =================

        System.out.print("Enter Room ID: ");
        Long roomId = scanner.nextLong();

        // Clear scanner buffer
        scanner.nextLine();

        // ================= CHECK-IN DATE =================

        System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
        String checkInInput = scanner.nextLine().trim();

        LocalDate checkInDate;

        try {
            checkInDate = LocalDate.parse(
                    checkInInput,
                    DateTimeFormatter.ISO_LOCAL_DATE
            );
        } catch (Exception e) {
            System.out.println("Invalid check-in date.");
            System.out.println("Please use format: YYYY-MM-DD");
            scanner.close();
            return;
        }

        // ================= CHECK-OUT DATE =================

        System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
        String checkOutInput = scanner.nextLine().trim();

        LocalDate checkOutDate;

        try {
            checkOutDate = LocalDate.parse(
                    checkOutInput,
                    DateTimeFormatter.ISO_LOCAL_DATE
            );
        } catch (Exception e) {
            System.out.println("Invalid check-out date.");
            System.out.println("Please use format: YYYY-MM-DD");
            scanner.close();
            return;
        }

        // ================= DATE VALIDATION =================

        if (!checkOutDate.isAfter(checkInDate)) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("BOOKING FAILED");
            System.out.println("=================================");
            System.out.println(
                    "Check-out date must be after check-in date."
            );

            scanner.close();
            return;
        }

        // ================= GUESTS =================

        System.out.print("Enter Number of Guests: ");
        Integer guests = scanner.nextInt();

        if (guests <= 0) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("BOOKING FAILED");
            System.out.println("=================================");
            System.out.println(
                    "Number of guests must be greater than 0."
            );

            scanner.close();
            return;
        }

        // ================= TOTAL AMOUNT =================

        System.out.print("Enter Total Amount: ");
        BigDecimal totalAmount = scanner.nextBigDecimal();

        if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("BOOKING FAILED");
            System.out.println("=================================");
            System.out.println(
                    "Total amount cannot be negative."
            );

            scanner.close();
            return;
        }

        // ================= CREATE BOOKING OBJECT =================

        Booking booking = new Booking();

        booking.setUserId(userId);
        booking.setHotelId(hotelId);
        booking.setRoomId(roomId);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setGuests(guests);
        booking.setTotalAmount(totalAmount);
        booking.setBookingStatus("CONFIRMED");

        // ================= DISPLAY INPUT =================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       BOOKING DETAILS");
        System.out.println("=================================");
        System.out.println("User ID       : " + userId);
        System.out.println("Hotel ID      : " + hotelId);
        System.out.println("Room ID       : " + roomId);
        System.out.println("Check-in      : " + checkInDate);
        System.out.println("Check-out     : " + checkOutDate);
        System.out.println("Guests        : " + guests);
        System.out.println("Total Amount  : " + totalAmount);
        System.out.println("Status        : CONFIRMED");
        System.out.println("=================================");

        // ================= CREATE BOOKING =================

        System.out.println();
        System.out.println("Creating booking...");

        Long bookingId =
                bookingController.createBooking(booking);

        // ================= RESULT =================

        if (bookingId != null) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("       BOOKING SUCCESSFUL");
            System.out.println("=================================");
            System.out.println("Booking ID    : " + bookingId);
            System.out.println("User ID       : " + userId);
            System.out.println("Hotel ID      : " + hotelId);
            System.out.println("Room ID       : " + roomId);
            System.out.println("Check-in      : " + checkInDate);
            System.out.println("Check-out     : " + checkOutDate);
            System.out.println("Guests        : " + guests);
            System.out.println("Total Amount  : " + totalAmount);
            System.out.println("Status        : CONFIRMED");
          System.out.println("=================================");

        } else {

            System.out.println();
            System.out.println("=================================");
            System.out.println("         BOOKING FAILED");
            System.out.println("=================================");
            System.out.println(
                    "Unable to create the booking."
            );
            System.out.println("=================================");
        }

        scanner.close();
    }
}