package com.booking;

import com.booking.dao.HotelDAO;
import com.booking.model.Hotel;

import java.math.BigDecimal;

public class HotelDAOTest {

    public static void main(String[] args) {

        HotelDAO hotelDAO = new HotelDAO();

        // ================= CREATE =================
        System.out.println("---- CREATE HOTEL ----");

        Hotel hotel = new Hotel();

        hotel.setLocationId(1L);
        hotel.setName("Test Hotel");
        hotel.setDescription("Test hotel for DAO");
        hotel.setAddress("Hyderabad");
        hotel.setStarRating(new BigDecimal("4.5"));
        hotel.setAmenities("WiFi, Pool, Parking");
        hotel.setStatus("ACTIVE");

        Long hotelId = hotelDAO.createHotel(hotel);

        System.out.println("Created Hotel ID: " + hotelId);


        // ================= READ =================
        System.out.println("\n---- READ HOTEL ----");

        Hotel savedHotel = hotelDAO.getHotelById(hotelId);

        if (savedHotel != null) {

            System.out.println("ID: " + savedHotel.getHotelId());
            System.out.println("Name: " + savedHotel.getName());
            System.out.println("Description: " + savedHotel.getDescription());
            System.out.println("Address: " + savedHotel.getAddress());
            System.out.println("Star Rating: " + savedHotel.getStarRating());
            System.out.println("Amenities: " + savedHotel.getAmenities());
            System.out.println("Status: " + savedHotel.getStatus());

        } else {
            System.out.println("Hotel not found.");
        }


        // ================= READ ALL =================
        System.out.println("\n---- READ ALL HOTELS ----");

        for (Hotel h : hotelDAO.getAllHotels()) {

            System.out.println(
                    h.getHotelId() + " | " +
                            h.getName() + " | " +
                            h.getAddress() + " | " +
                            h.getStatus()
            );
        }


        // ================= UPDATE =================
        System.out.println("\n---- UPDATE HOTEL ----");

        savedHotel.setName("Updated Test Hotel");
        savedHotel.setAddress("Updated Hyderabad");
        savedHotel.setStarRating(new BigDecimal("5.0"));

        boolean updated = hotelDAO.updateHotel(savedHotel);

        System.out.println("Hotel updated: " + updated);


        // ================= READ UPDATED =================
        System.out.println("\n---- READ UPDATED HOTEL ----");

        Hotel updatedHotel = hotelDAO.getHotelById(hotelId);

        if (updatedHotel != null) {

            System.out.println("Name: " + updatedHotel.getName());
            System.out.println("Address: " + updatedHotel.getAddress());
            System.out.println("Star Rating: " + updatedHotel.getStarRating());
        }


        // ================= DELETE =================
        System.out.println("\n---- DELETE HOTEL ----");

        boolean deleted = hotelDAO.deleteHotel(hotelId);

        System.out.println("Hotel deleted: " + deleted);


        System.out.println("\nHotel DAO CRUD test completed!");
    }
}