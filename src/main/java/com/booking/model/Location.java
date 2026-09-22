package com.booking.model;

import java.time.LocalDateTime;

public class Location {

    private Long locationId;
    private String name;
    private String type;
    private Long parentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Location() {
    }

    public Location(Long locationId, String name, String type,
                    Long parentId, LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        this.locationId = locationId;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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