package com.huawei.devcloud.monitor.model;

public class JenkinsResource {
    private String name;
    private String url;
    private String version;
    private String status;
    private int clusterId;
    private Vmformat vmformat;
    private JenkinsAccessInfo accessInfo;

    public JenkinsResource(String name, String url, String version, String status, int clusterId, Vmformat vmformat,
                           JenkinsAccessInfo accessInfo) {
        this.name = name;
        this.url = url;
        this.version = version;
        this.status = status;
        this.clusterId = clusterId;
        this.vmformat = vmformat;
        this.accessInfo = accessInfo;
    }

    @Override
    public String toString() {
        return "JenkinsResource{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                ", status='" + status + '\'' +
                ", clusterId=" + clusterId +
                ", vmformat=" + vmformat +
                ", accessInfo=" + accessInfo +
                '}';
    }
}
