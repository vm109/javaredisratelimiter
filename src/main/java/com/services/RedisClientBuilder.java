package com.services;

import com.configurations.JavaRateLimiterClientConfiguration;
import redis.clients.jedis.Jedis;

public class RedisClientBuilder {
    private JavaRateLimiterClientConfiguration config;

    private RedisClientBuilder(JavaRateLimiterClientConfiguration config) {
        this.config = config;
    }

    public static Jedis build(JavaRateLimiterClientConfiguration config) {
        return new Jedis(config.getRedisHost(), config.getRedisPort(), config.isRedisUseSsl());
    }
}
