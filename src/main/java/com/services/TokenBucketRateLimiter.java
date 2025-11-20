package com.services;
import com.configurations.JavaRateLimiterClientConfiguration;
import com.strategyobjects.TokenBucket;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TokenBucketRateLimiter implements RedisRateLimiter {
    private final Jedis jedis;
    private TokenBucket tokenBucket;

    public TokenBucketRateLimiter(JavaRateLimiterClientConfiguration javaRateLimiterClientConfiguration, TokenBucket tokenBucket) {
        this.jedis = RedisClientBuilder.build(javaRateLimiterClientConfiguration);
        this.tokenBucket = tokenBucket;
    }

    @Override
    public boolean allowRequest(String clientId) {
        String keyCount = "token_bucket_ratelimiter:{" + clientId + "}:token_count";
        String keyLastRefill = "token_bucket_ratelimiter:{" + clientId + "}:last_refill_timestamp";

        Transaction transaction = jedis.multi();
        transaction.get(keyCount);
        transaction.get(keyLastRefill);
        var results = transaction.exec();

        long currentTime = System.currentTimeMillis();
        long tokenCount = results.get(0) != null ? Long.parseLong((String) results.get(0)) : tokenBucket.getCapacity();
        long lastRefillTimestamp = results.get(1) != null ? Long.parseLong((String) results.get(1)) : currentTime;

        long elapsedTime = currentTime - lastRefillTimestamp;
        double elapsedTimeSeconds = elapsedTime / 1000.0;
        long tokensToAdd = (long) (elapsedTimeSeconds * tokenBucket.getRefillRate());

        tokenCount = Math.min(tokenBucket.getCapacity(), tokenCount + tokensToAdd);

        if(tokenCount > 0) {
            tokenCount--;

            transaction = jedis.multi();
            transaction.set(keyCount, String.valueOf(tokenCount));
            transaction.set(keyLastRefill, String.valueOf(currentTime));
            transaction.exec();

            return true;
        }

        return false;
    }
}
