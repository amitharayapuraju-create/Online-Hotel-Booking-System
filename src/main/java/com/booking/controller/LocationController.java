package com.booking.controller;

import com.booking.model.Location;
import com.booking.service.LocationService;

import java.util.List;

public class LocationController {

    private final LocationService locationService;

    public LocationController() {
        this.locationService = new LocationService();
    }

    // ================= CREATE =================

    public Long createLocation(Location location) {

        if (location == null) {
            System.out.println("Invalid location");
            return null;
        }

        System.out.println("Creating location...");

        Long locationId = locationService.createLocation(location);

        if (locationId != null) {
            System.out.println("Location created successfully");
            System.out.println("Location ID: " + locationId);
        } else {
            System.out.println("Location creation failed");
        }

        return locationId;
    }

    // ================= READ BY ID =================

    public Location getLocationById(Long locationId) {

        if (locationId == null || locationId <= 0) {
            System.out.println("Invalid location ID");
            return null;
        }

        System.out.println("Fetching location with ID: " + locationId);

        Location location =
                locationService.getLocationById(locationId);

        if (location != null) {
            System.out.println("Location found");
            System.out.println("Location ID: " + location.getLocationId());
            System.out.println("Name: " + location.getName());
            System.out.println("Type: " + location.getType());
        } else {
            System.out.println("Location not found");
        }

        return location;
    }

    // ================= READ ALL =================

    public List<Location> getAllLocations() {

        System.out.println("Fetching all locations...");

        List<Location> locations =
                locationService.getAllLocations();

        System.out.println("Total locations: " + locations.size());

        return locations;
    }

    // ================= UPDATE =================

    public boolean updateLocation(Location location) {

        if (location == null ||
                location.getLocationId() == null ||
                location.getLocationId() <= 0) {

            System.out.println("Invalid location for update");
            return false;
        }

        System.out.println(
                "Updating location with ID: "
                        + location.getLocationId());

        boolean updated =
                locationService.updateLocation(location);

        if (updated) {
            System.out.println("Location updated successfully");
        } else {
            System.out.println("Location update failed");
        }

        return updated;
    }

    // ================= DELETE =================

    public boolean deleteLocation(Long locationId) {

        if (locationId == null || locationId <= 0) {
            System.out.println("Invalid location ID");
            return false;
        }

        System.out.println(
                "Deleting location with ID: " + locationId);

        boolean deleted =
                locationService.deleteLocation(locationId);

        if (deleted) {
            System.out.println("Location deleted successfully");
        } else {
            System.out.println("Location deletion failed");
        }

        return deleted;
    }
}