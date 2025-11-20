package com.configurations;

public class JavaRateLimiterClientConfiguration {
    private String redisHost;
    private int redisPort;
    private boolean redisUseSsl;
    private String redisPassword;
    private int redisDatabase;
    private String rateLimitStrategy;

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public boolean isRedisUseSsl() {
        return redisUseSsl;
    }

    public void setRedisUseSsl(boolean redisUseSsl) {
        this.redisUseSsl = redisUseSsl;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public void setRedisPassword(String redisPassword) {
        this.redisPassword = redisPassword;
    }

    public int getRedisDatabase() {
        return redisDatabase;
    }

    public void setRedisDatabase(int redisDatabase) {
        this.redisDatabase = redisDatabase;
    }

    public String getRateLimitStrategy() {
        return rateLimitStrategy;
    }

    public void setRateLimitStrategy(String rateLimitStrategy) {
        this.rateLimitStrategy = rateLimitStrategy;
    }
}
