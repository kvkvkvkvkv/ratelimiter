package com.example.service;

import com.example.model.Request;

public interface RateLimiterService {
    boolean limit(Request request);
}
