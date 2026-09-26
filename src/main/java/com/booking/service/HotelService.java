package com.booking.service;

import com.booking.dao.HotelDAO;
import com.booking.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HotelService {

    private static final Logger logger =
            LoggerFactory.getLogger(HotelService.class);

    private final HotelDAO hotelDAO;

    public HotelService() {
        this.hotelDAO = new HotelDAO();
    }

    // ================= CREATE =================

    public Long createHotel(Hotel hotel) {

        if (hotel == null) {
            logger.error("Hotel cannot be null");
            return null;
        }

        logger.info("Creating hotel: {}", hotel.getName());

        Long hotelId = hotelDAO.createHotel(hotel);

        if (hotelId != null) {
            logger.info("Hotel created successfully with ID: {}", hotelId);
        } else {
            logger.error("Failed to create hotel");
        }

        return hotelId;
    }

    // ================= READ BY ID =================

    public Hotel getHotelById(Long hotelId) {

        if (hotelId == null || hotelId <= 0) {
            logger.error("Invalid hotel ID: {}", hotelId);
            return null;
        }

        logger.info("Fetching hotel with ID: {}", hotelId);

        Hotel hotel = hotelDAO.getHotelById(hotelId);

        if (hotel != null) {
            logger.info("Hotel found with ID: {}", hotelId);
        } else {
            logger.warn("Hotel not found with ID: {}", hotelId);
        }

        return hotel;
    }

    // ================= READ ALL =================

    public List<Hotel> getAllHotels() {

        logger.info("Fetching all hotels");

        List<Hotel> hotels = hotelDAO.getAllHotels();

        logger.info("Total hotels fetched: {}", hotels.size());

        return hotels;
    }

    // ================= UPDATE =================

    public boolean updateHotel(Hotel hotel) {

        if (hotel == null || hotel.getHotelId() == null) {
            logger.error("Invalid hotel for update");
            return false;
        }

        logger.info("Updating hotel with ID: {}",
                hotel.getHotelId());

        boolean updated = hotelDAO.updateHotel(hotel);

        if (updated) {
            logger.info("Hotel updated successfully with ID: {}",
                    hotel.getHotelId());
        } else {
            logger.warn("Hotel update failed for ID: {}",
                    hotel.getHotelId());
        }

        return updated;
    }

    // ================= DELETE =================

    public boolean deleteHotel(Long hotelId) {

        if (hotelId == null || hotelId <= 0) {
            logger.error("Invalid hotel ID: {}", hotelId);
            return false;
        }

        logger.info("Deleting hotel with ID: {}", hotelId);

        boolean deleted = hotelDAO.deleteHotel(hotelId);

        if (deleted) {
            logger.info("Hotel deleted successfully with ID: {}",
                    hotelId);
        } else {
            logger.warn("Hotel deletion failed for ID: {}",
                    hotelId);
        }

        return deleted;
    }
}