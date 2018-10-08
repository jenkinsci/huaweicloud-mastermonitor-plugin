package com.huawei.devcloud.monitor.model;

import java.util.Map;
import java.util.Set;

public class BasicInfoBean {


    private String url = null;


    private Map<String,Set<String>> labels = null;

    private String version = null;
    

    private transient  AccessInfo accessInfo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Set<String>> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, Set<String>> labels) {
        this.labels = labels;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    @Override
    public String toString() {
        return "BasicInfoBean{" +
                "url='" + url + '\'' +
                ", labels=" + labels +
                ", version='" + version + '\'' +
                ", accessInfo=" + accessInfo +
                '}';
    }
}
