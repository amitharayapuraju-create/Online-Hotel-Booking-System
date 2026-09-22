package com.booking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Hotel {

    private Long hotelId;
    private Long locationId;
    private String name;
    private String description;
    private String address;
    private BigDecimal starRating;
    private String amenities;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Hotel() {
    }

    public Hotel(Long hotelId, Long locationId, String name,
                 String description, String address,
                 BigDecimal starRating, String amenities,
                 String status, LocalDateTime createdAt,
                 LocalDateTime updatedAt) {
        this.hotelId = hotelId;
        this.locationId = locationId;
        this.name = name;
        this.description = description;
        this.address = address;
        this.starRating = starRating;
        this.amenities = amenities;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getStarRating() {
        return starRating;
    }

    public void setStarRating(BigDecimal starRating) {
        this.starRating = starRating;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}