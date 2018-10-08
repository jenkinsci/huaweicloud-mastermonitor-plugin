package com.huawei.devcloud.monitor;

import com.huawei.devcloud.global_configuration.DevCloudGlobalConfiguration;
import com.huawei.devcloud.monitor.model.JenkinsBean;

public interface JenkinsInfoGenerator {
    public JenkinsBean generateJenkinsInfo(DevCloudGlobalConfiguration devcloudConfig);
}
