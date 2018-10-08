package com.huawei.devcloud.monitor.model;


public class HttpCount implements HttpCountInfo{

    private int allHttpCount = 0;


    private int httpErrorCount = 0;

    public HttpCount(int allHttpCount, int httpErrorCount) {
        this.allHttpCount = allHttpCount;
        this.httpErrorCount = httpErrorCount;
    }

    public int getAllHttpCount() {
        return allHttpCount;
    }

    public void setAllHttpCount(int allHttpCount) {
        this.allHttpCount = allHttpCount;
    }

    public int getHttpErrorCount() {
        return httpErrorCount;
    }

    public void setHttpErrorCount(int httpErrorCount) {
        this.httpErrorCount = httpErrorCount;
    }

    @Override
    public String toString() {
        return "HttpCount{" +
                "allHttpCount=" + allHttpCount +
                ", httpErrorCount=" + httpErrorCount +
                '}';
    }
}
