package com.huawei.devcloud.monitor.model;

public class JenkinsAccessInfo {
    private String user;
    private String password;
    private String token;

    public JenkinsAccessInfo(String user, String password, String token) {
        this.user = user;
        this.password = password;
        this.token = token;
    }

    @Override
    public String toString() {
        return "JenkinsAccessInfo{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
