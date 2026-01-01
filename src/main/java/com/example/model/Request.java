package com.example.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Request {
    UUID userId;
    LocalDateTime localDateTime;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Request{" +
                "userId=" + userId +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
