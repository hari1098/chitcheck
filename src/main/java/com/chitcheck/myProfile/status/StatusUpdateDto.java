package com.chitcheck.myProfile.status;

import java.time.LocalDateTime;
import java.util.Objects;

public class StatusUpdateDto {

    private String description;
    private String statusType;
    private LocalDateTime timestamp;

    // 1. No-argument constructor
    public StatusUpdateDto() {
    }

    // 2. All-arguments constructor
    public StatusUpdateDto(String description, String statusType, LocalDateTime timestamp) {
        this.description = description;
        this.statusType = statusType;
        this.timestamp = timestamp;
    }

    // 3. Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // 4. equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusUpdateDto that = (StatusUpdateDto) o;
        return Objects.equals(description, that.description) && Objects.equals(statusType, that.statusType) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, statusType, timestamp);
    }

    @Override
    public String toString() {
        return "StatusUpdateDto{" +
                "description='" + description + '\'' +
                ", statusType='" + statusType + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}