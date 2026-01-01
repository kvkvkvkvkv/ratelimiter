package com.example;

import com.example.model.Request;
import com.example.repository.RateLimitRepository;
import com.example.repository.RateLimitRepositoryImpl;
import com.example.service.RateLimiterService;
import com.example.service.SlidingWindowService;

import java.time.LocalDateTime;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RateLimitRepository rateLimitRepository = new RateLimitRepositoryImpl();
        RateLimiterService rateLimiterService = new SlidingWindowService(rateLimitRepository, 2, 4);

        UUID user1 = UUID.randomUUID();
        UUID user2 = UUID.randomUUID();
        UUID user3 = UUID.randomUUID();

        Request request1 = build(user1);
        Request request2 = build(user2);
        Request request3 = build(user3);
        Request request4 = build(user1);
        Request request5 = build(user2);
        Request request6 = build(user2);
        Request request7 = build(user1);
        Request request8 = build(user3);
        Request request9 = build(user3);
        Request request10 = build(user3);
        Request request11 = build(user3);
        Request request12 = build(user1);
        Request request13 = build(user1);
        Request request14 = build(user2);

        rateLimiterService.limit(request1);
        rateLimiterService.limit(request2);
        rateLimiterService.limit(request3);
        rateLimiterService.limit(request4);
        rateLimiterService.limit(request5);
        rateLimiterService.limit(request6);
        rateLimiterService.limit(request7);
        rateLimiterService.limit(request8);
        rateLimiterService.limit(request9);
        rateLimiterService.limit(request10);
        rateLimiterService.limit(request11);
        rateLimiterService.limit(request12);
        rateLimiterService.limit(request13);
        rateLimiterService.limit(request14);
    }

    static Request build(UUID user) {
        Request request = new Request();
        request.setUserId(user);
        request.setLocalDateTime(LocalDateTime.now());
        return request;
    }
}