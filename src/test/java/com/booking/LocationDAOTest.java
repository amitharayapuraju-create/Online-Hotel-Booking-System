package com.booking;

import com.booking.dao.LocationDAO;
import com.booking.model.Location;

import java.util.List;

public class LocationDAOTest {

    public static void main(String[] args) {

        LocationDAO locationDAO = new LocationDAO();

        // ================= CREATE =================
        System.out.println("---- CREATE LOCATION ----");

        Location location = new Location();
        location.setName("Test City");
        location.setType("CITY");
        location.setParentId(null);

        Long locationId = locationDAO.createLocation(location);

        System.out.println("Created Location ID: " + locationId);


        // ================= READ BY ID =================
        System.out.println("\n---- READ LOCATION ----");

        Location foundLocation =
                locationDAO.getLocationById(locationId);

        if (foundLocation != null) {
            System.out.println("ID: " + foundLocation.getLocationId());
            System.out.println("Name: " + foundLocation.getName());
            System.out.println("Type: " + foundLocation.getType());
            System.out.println("Parent ID: " + foundLocation.getParentId());
        } else {
            System.out.println("Location not found.");
        }


        // ================= READ ALL =================
        System.out.println("\n---- READ ALL LOCATIONS ----");

        List<Location> locations =
                locationDAO.getAllLocations();

        System.out.println("Total locations: " + locations.size());

        for (Location loc : locations) {
            System.out.println(
                    loc.getLocationId() + " - "
                            + loc.getName() + " - "
                            + loc.getType()
            );
        }


        // ================= UPDATE =================
        System.out.println("\n---- UPDATE LOCATION ----");

        foundLocation.setName("Updated Test City");

        boolean updated =
                locationDAO.updateLocation(foundLocation);

        System.out.println("Updated: " + updated);


        // ================= READ UPDATED =================
        System.out.println("\n---- READ UPDATED LOCATION ----");

        Location updatedLocation =
                locationDAO.getLocationById(locationId);

        if (updatedLocation != null) {
            System.out.println("Name: "
                    + updatedLocation.getName());
        }


        // ================= DELETE =================
        System.out.println("\n---- DELETE LOCATION ----");

        boolean deleted =
                locationDAO.deleteLocation(locationId);

        System.out.println("Deleted: " + deleted);


        System.out.println("\nLocation DAO CRUD test completed!");
    }
}