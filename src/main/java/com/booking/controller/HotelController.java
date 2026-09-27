package com.booking.controller;

import com.booking.model.Hotel;
import com.booking.service.HotelService;

import java.util.List;

public class HotelController {

    private final HotelService hotelService;

    public HotelController() {
        this.hotelService = new HotelService();
    }

    // ================= CREATE =================

    public Long createHotel(Hotel hotel) {

        if (hotel == null) {
            System.out.println("Hotel cannot be null");
            return null;
        }

        System.out.println("Creating hotel...");

        Long hotelId = hotelService.createHotel(hotel);

        if (hotelId != null) {
            System.out.println(
                    "Hotel created successfully with ID: " + hotelId);
        } else {
            System.out.println("Failed to create hotel");
        }

        return hotelId;
    }

    // ================= READ BY ID =================

    public Hotel getHotelById(Long hotelId) {

        if (hotelId == null || hotelId <= 0) {
            System.out.println("Invalid hotel ID");
            return null;
        }

        System.out.println(
                "Fetching hotel with ID: " + hotelId);

        Hotel hotel = hotelService.getHotelById(hotelId);

        if (hotel != null) {
            System.out.println("Hotel found");
        } else {
            System.out.println("Hotel not found");
        }

        return hotel;
    }

    // ================= READ ALL =================

    public List<Hotel> getAllHotels() {

        System.out.println("Fetching all hotels...");

        List<Hotel> hotels = hotelService.getAllHotels();

        System.out.println(
                "Total hotels: " + hotels.size());

        return hotels;
    }

    // ================= UPDATE =================

    public boolean updateHotel(Hotel hotel) {

        if (hotel == null || hotel.getHotelId() == null) {
            System.out.println("Invalid hotel for update");
            return false;
        }

        System.out.println(
                "Updating hotel with ID: " + hotel.getHotelId());

        boolean updated = hotelService.updateHotel(hotel);

        if (updated) {
            System.out.println("Hotel updated successfully");
        } else {
            System.out.println("Hotel update failed");
        }

        return updated;
    }

    // ================= DELETE =================

    public boolean deleteHotel(Long hotelId) {

        if (hotelId == null || hotelId <= 0) {
            System.out.println("Invalid hotel ID");
            return false;
        }

        System.out.println(
                "Deleting hotel with ID: " + hotelId);

        boolean deleted = hotelService.deleteHotel(hotelId);

        if (deleted) {
            System.out.println("Hotel deleted successfully");
        } else {
            System.out.println("Hotel deletion failed");
        }

        return deleted;
    }
}
