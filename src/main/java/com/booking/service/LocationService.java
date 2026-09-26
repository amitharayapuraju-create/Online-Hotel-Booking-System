package com.booking.service;

import com.booking.dao.LocationDAO;
import com.booking.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LocationService {

    private static final Logger logger =
            LoggerFactory.getLogger(LocationService.class);

    private final LocationDAO locationDAO;

    public LocationService() {
        this.locationDAO = new LocationDAO();
    }

    // ================= CREATE =================

    public Long createLocation(Location location) {

        if (location == null) {
            logger.error("Location cannot be null");
            return null;
        }

        logger.info("Creating location");

        Long locationId = locationDAO.createLocation(location);

        if (locationId != null) {
            logger.info("Location created successfully with ID: {}",
                    locationId);
        } else {
            logger.error("Failed to create location");
        }

        return locationId;
    }

    // ================= READ BY ID =================

    public Location getLocationById(Long locationId) {

        if (locationId == null || locationId <= 0) {
            logger.error("Invalid location ID: {}", locationId);
            return null;
        }

        logger.info("Fetching location with ID: {}", locationId);

        Location location = locationDAO.getLocationById(locationId);

        if (location != null) {
            logger.info("Location found with ID: {}", locationId);
        } else {
            logger.warn("Location not found with ID: {}", locationId);
        }

        return location;
    }

    // ================= READ ALL =================

    public List<Location> getAllLocations() {

        logger.info("Fetching all locations");

        List<Location> locations = locationDAO.getAllLocations();

        logger.info("Total locations fetched: {}", locations.size());

        return locations;
    }

    // ================= UPDATE =================

    public boolean updateLocation(Location location) {

        if (location == null || location.getLocationId() == null) {
            logger.error("Invalid location for update");
            return false;
        }

        logger.info("Updating location with ID: {}",
                location.getLocationId());

        boolean updated = locationDAO.updateLocation(location);

        if (updated) {
            logger.info("Location updated successfully with ID: {}",
                    location.getLocationId());
        } else {
            logger.warn("Location update failed for ID: {}",
                    location.getLocationId());
        }

        return updated;
    }

    // ================= DELETE =================

    public boolean deleteLocation(Long locationId) {

        if (locationId == null || locationId <= 0) {
            logger.error("Invalid location ID: {}", locationId);
            return false;
        }

        logger.info("Deleting location with ID: {}", locationId);

        boolean deleted = locationDAO.deleteLocation(locationId);

        if (deleted) {
            logger.info("Location deleted successfully with ID: {}",
                    locationId);
        } else {
            logger.warn("Location deletion failed for ID: {}",
                    locationId);
        }

        return deleted;
    }
}