package com.example.service;

import com.example.model.Request;
import com.example.repository.RateLimitRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

public class SlidingWindowService implements RateLimiterService {

    Logger logger = Logger.getLogger(SlidingWindowService.class.getName());

    RateLimitRepository rateLimitRepository;
    int maxRequests;
    int windowSize;

    public SlidingWindowService(RateLimitRepository rateLimitRepository, int maxRequests, int windowSize) {
        this.rateLimitRepository = rateLimitRepository;
        this.maxRequests = maxRequests;
        this.windowSize = windowSize;
    }

    @Override
    public boolean limit(Request request) {
        LocalDateTime now = LocalDateTime.now();
        Queue<Request> window = rateLimitRepository.getWindow(request.getUserId());

        while(!window.isEmpty() && Duration.between(window.peek().getLocalDateTime(), now).toNanos() > windowSize) {
            Request polled = window.poll();
            logger.info(polled.toString());
        }
        if (window.size()<maxRequests) {
            window.add(request);
            rateLimitRepository.updateWindow(Map.entry(request.getUserId(), window));
            return true;
        }
        return false;
    }
}
