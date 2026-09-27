package com.booking.controller;

import com.booking.model.Hotel;

public class HotelControllerTest {

    public static void main(String[] args) {

        System.out.println("==============================");
        System.out.println("      HOTEL CONTROLLER TEST");
        System.out.println("==============================");

        HotelController hotelController =
                new HotelController();

        // ================= INVALID CREATE =================

        System.out.println("\n--- TEST CREATE WITH NULL ---");

        Long hotelId =
                hotelController.createHotel(null);

        System.out.println("Result: " + hotelId);


        // ================= INVALID GET BY ID =================

        System.out.println("\n--- TEST GET HOTEL WITH INVALID ID ---");

        Hotel hotel =
                hotelController.getHotelById(-1L);

        System.out.println("Result: " + hotel);


        // ================= GET ALL =================

        System.out.println("\n--- TEST GET ALL HOTELS ---");

        hotelController.getAllHotels();


        // ================= INVALID UPDATE =================

        System.out.println("\n--- TEST UPDATE WITH NULL ---");

        boolean updated =
                hotelController.updateHotel(null);

        System.out.println("Updated: " + updated);


        // ================= INVALID DELETE =================

        System.out.println("\n--- TEST DELETE WITH INVALID ID ---");

        boolean deleted =
                hotelController.deleteHotel(-1L);

        System.out.println("Deleted: " + deleted);


        // ================= COMPLETE =================

        System.out.println("\n==============================");
        System.out.println("    HOTEL CONTROLLER TEST DONE");
        System.out.println("==============================");
    }
}