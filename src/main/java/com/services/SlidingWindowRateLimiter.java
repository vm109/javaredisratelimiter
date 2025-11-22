package com.services;

import com.configurations.JavaRateLimiterClientConfiguration;
import com.strategyobjects.SlidingWindow;
import redis.clients.jedis.Jedis;

public class SlidingWindowRateLimiter implements RedisRateLimiter {
    private Jedis jedis;
    private SlidingWindow slidingWindow;
    public SlidingWindowRateLimiter(JavaRateLimiterClientConfiguration config, SlidingWindow slidingWindow) {
        this.jedis = RedisClientBuilder.build(config);
        this.slidingWindow = slidingWindow;
    }

    @Override
    public boolean allowRequest(String clientId) {

        String key = "sliding_window_ratelimiter:{" + clientId + "}:requests";
        long currentTime = System.currentTimeMillis();
        long windowStart = currentTime - slidingWindow.getWindowSizeinMilliseconds();

        jedis.zremrangeByScore(key, 0, windowStart);
        jedis.zadd(key, currentTime, String.valueOf(currentTime));
        long requestCount = jedis.zcard(key);

        return requestCount <= slidingWindow.getMaxRequests();
    }
}
