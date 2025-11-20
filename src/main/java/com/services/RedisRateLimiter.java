package com.services;

public interface RedisRateLimiter {
    boolean allowRequest(String clientId);
}
