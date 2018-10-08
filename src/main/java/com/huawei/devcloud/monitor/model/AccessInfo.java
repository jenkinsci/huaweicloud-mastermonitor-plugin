package com.huawei.devcloud.monitor.model;

import hudson.model.User;
import jenkins.model.Jenkins;

public class AccessInfo {
    private String user;
    private String token;

    public static final String TOKEN_HIDDEN = "Token is hidden";

    public AccessInfo(String user) {
        this.user = user;
        this.token = getAPIToken(user);
    }

    public AccessInfo(String user, String token) {
        this.user = user;
        this.token = token;
    }


    public String getAPIToken(String userName) {
        User user = Jenkins.getInstance().getUser(userName);
        if (null != user) {
            token = user.getProperty
                    (jenkins.security.ApiTokenProperty.class).getApiToken();
        }
        return token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AccessInfo{" +
                "user='" + user + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
