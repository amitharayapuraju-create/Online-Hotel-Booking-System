package com.booking;

import com.booking.dao.LocationDAO;
import com.booking.model.Location;

public class LocationDAOTest {

    public static void main(String[] args) {

        LocationDAO locationDAO = new LocationDAO();

        System.out.println("----- READ LOCATION -----");

        Location location = locationDAO.getLocationById(1L);

        if (location != null) {
            System.out.println("ID: " + location.getLocationId());
            System.out.println("Name: " + location.getName());
            System.out.println("Type: " + location.getType());
            System.out.println("Parent ID: " + location.getParentId());
        } else {
            System.out.println("Location not found.");
        }
    }
}