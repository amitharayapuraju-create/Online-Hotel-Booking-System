package com.booking.service;

import com.booking.model.Location;

import java.util.List;

public class LocationServiceTest {

    public static void main(String[] args) {

        LocationService locationService = new LocationService();

        // =========================================================
        // CREATE LOCATION
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("          CREATE LOCATION");
        System.out.println("========================================");

        Location location = new Location();

        location.setName("Kansas City");
        location.setType("CITY");
        location.setParentId(null);

        Long locationId = locationService.createLocation(location);

        System.out.println("Created Location ID: " + locationId);

        if (locationId == null) {
            System.out.println("Location creation failed.");
            return;
        }


        // =========================================================
        // READ LOCATION BY ID
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("       READ LOCATION BY ID");
        System.out.println("========================================");

        Location fetchedLocation =
                locationService.getLocationById(locationId);

        if (fetchedLocation != null) {

            System.out.println("Location fetched successfully.");
            System.out.println("Location ID: "
                    + fetchedLocation.getLocationId());
            System.out.println("Name: "
                    + fetchedLocation.getName());
            System.out.println("Type: "
                    + fetchedLocation.getType());

        } else {
            System.out.println("Location not found.");
        }


        // =========================================================
        // READ ALL LOCATIONS
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("          READ ALL LOCATIONS");
        System.out.println("========================================");

        List<Location> locations =
                locationService.getAllLocations();

        System.out.println("Total Locations: " + locations.size());

        for (Location loc : locations) {

            System.out.println(
                    "ID: " + loc.getLocationId()
                            + " | Name: " + loc.getName()
                            + " | Type: " + loc.getType()
            );
        }


        // =========================================================
        // UPDATE LOCATION
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("          UPDATE LOCATION");
        System.out.println("========================================");

        location.setLocationId(locationId);
        location.setName("Updated Kansas City");
        location.setType("CITY");

        boolean updated =
                locationService.updateLocation(location);

        System.out.println("Location updated: " + updated);


        // =========================================================
        // READ UPDATED LOCATION
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("       READ UPDATED LOCATION");
        System.out.println("========================================");

        Location updatedLocation =
                locationService.getLocationById(locationId);

        if (updatedLocation != null) {

            System.out.println("Updated Location ID: "
                    + updatedLocation.getLocationId());
            System.out.println("Updated Name: "
                    + updatedLocation.getName());
            System.out.println("Updated Type: "
                    + updatedLocation.getType());

        } else {
            System.out.println("Updated location not found.");
        }


        // =========================================================
        // DELETE LOCATION
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("          DELETE LOCATION");
        System.out.println("========================================");

        boolean deleted =
                locationService.deleteLocation(locationId);

        System.out.println("Location deleted: " + deleted);


        // =========================================================
        // TEST COMPLETE
        // =========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("       LOCATION SERVICE TEST DONE");
        System.out.println("========================================");
    }
}