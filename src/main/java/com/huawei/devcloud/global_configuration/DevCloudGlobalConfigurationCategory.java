package com.huawei.devcloud.global_configuration;

import hudson.Extension;
import jenkins.model.GlobalConfigurationCategory;

@Extension
public class DevCloudGlobalConfigurationCategory extends GlobalConfigurationCategory {

    @Override
    public String getDisplayName() {
        return "DevCloud Configuration";
    }

    @Override
    public String getShortDescription() {
        return "DevCloud Configuration";
    }

}
