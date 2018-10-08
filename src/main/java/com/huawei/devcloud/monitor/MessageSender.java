package com.huawei.devcloud.monitor;

import com.huawei.devcloud.monitor.model.JenkinsBean;

public interface MessageSender {
    public void send(String url, JenkinsBean bean);
}
