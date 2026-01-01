package com.example.repository;

import com.example.model.Request;

import java.util.Map;
import java.util.Queue;
import java.util.UUID;

public interface RateLimitRepository {
    void saveRequestRate(Request request);
    Map<UUID, Queue<Request>> getRateLimit();
    Queue<Request> getWindow(UUID userId);
    void updateWindow(Map.Entry<UUID, Queue<Request>> user);
}
