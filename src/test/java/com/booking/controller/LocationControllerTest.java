package com.booking.controller;

public class LocationControllerTest {

    public static void main(String[] args) {

        LocationController locationController =
                new LocationController();

        // ================= GET ALL =================

        System.out.println("\n--- TEST GET ALL LOCATIONS ---");

        locationController.getAllLocations();


        // ================= INVALID GET =================

        System.out.println("\n--- TEST GET LOCATION WITH INVALID ID ---");

        locationController.getLocationById(-1L);


        // ================= INVALID UPDATE =================

        System.out.println("\n--- TEST UPDATE WITH NULL ---");

        boolean updated =
                locationController.updateLocation(null);

        System.out.println("Updated: " + updated);


        // ================= INVALID DELETE =================

        System.out.println("\n--- TEST DELETE WITH INVALID ID ---");

        boolean deleted =
                locationController.deleteLocation(-1L);

        System.out.println("Deleted: " + deleted);


        // ================= DONE =================

        System.out.println("\n==============================");
        System.out.println(" LOCATION CONTROLLER TEST DONE");
        System.out.println("==============================");
    }
}