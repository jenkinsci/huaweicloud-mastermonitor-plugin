package com.huawei.devcloud.monitor.model;


public class ThreadCount implements ThreadCountInfo {
    private long allThreadCount = 0;

    private int activeThreadCount = 0;

    public ThreadCount(long allThreadCount, int activeThreadCount) {
        this.allThreadCount = allThreadCount;
        this.activeThreadCount = activeThreadCount;
    }

    public long getAllThreadCount() {
        return allThreadCount;
    }

    public void setAllThreadCount(long allThreadCount) {
        this.allThreadCount = allThreadCount;
    }

    public int getActiveThreadCount() {
        return activeThreadCount;
    }

    public void setActiveThreadCount(int activeThreadCount) {
        this.activeThreadCount = activeThreadCount;
    }

    @Override
    public String toString() {
        return "ThreadCount{" +
                "allThreadCount=" + allThreadCount +
                ", activeThreadCount=" + activeThreadCount +
                '}';
    }
}
