package com.strategyobjects;

public class SlidingWindow {
    private int windowSizeinMilliseconds;
    private int maxRequests;
    private long[] requestTimestamps;
    private int requestCount;

    public int getWindowSizeinMilliseconds() {
        return windowSizeinMilliseconds;
    }

    public void setWindowSizeinMilliseconds(int windowSizeinMilliseconds) {
        this.windowSizeinMilliseconds = windowSizeinMilliseconds;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public void setMaxRequests(int maxRequests) {
        this.maxRequests = maxRequests;
    }

    public long[] getRequestTimestamps() {
        return requestTimestamps;
    }

    public void setRequestTimestamps(long[] requestTimestamps) {
        this.requestTimestamps = requestTimestamps;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }
}
