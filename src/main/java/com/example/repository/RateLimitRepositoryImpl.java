package com.example.repository;

import com.example.model.Request;

import java.util.*;

public class RateLimitRepositoryImpl implements RateLimitRepository {

    Map<UUID, Queue<Request>> map = new HashMap<>();

    @Override
    public void saveRequestRate(Request request) {
        map.computeIfAbsent(request.getUserId(), id -> new LinkedList<>()).add(request);
    }

    @Override
    public Map<UUID, Queue<Request>> getRateLimit() {
        return map;
    }

    @Override
    public Queue<Request> getWindow(UUID userId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new LinkedList<>());
        }
        return map.get(userId);
    }

    @Override
    public void updateWindow(Map.Entry<UUID, Queue<Request>> user) {
        map.put(user.getKey(), user.getValue());
    }
}
