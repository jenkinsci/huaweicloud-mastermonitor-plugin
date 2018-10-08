package com.huawei.devcloud.monitor;

import com.google.gson.Gson;
import com.huawei.devcloud.global_configuration.DevCloudGlobalConfiguration;
import com.huawei.devcloud.monitor.model.JenkinsBean;
import hudson.Extension;
import hudson.model.AsyncPeriodicWork;
import hudson.model.PeriodicWork;
import hudson.model.TaskListener;
import jenkins.model.Jenkins;

import java.io.IOException;
import java.util.logging.Logger;

@Extension
public class MonitorWork extends AsyncPeriodicWork {
    private static final Logger LOGGER = Logger.getLogger(MonitorWork.class.getName());
    private DevCloudGlobalConfiguration devcloudConfig;

    public MonitorWork() {
        super("Monitor");
    }

    @Override
    public long getRecurrencePeriod() {
        devcloudConfig = (DevCloudGlobalConfiguration) Jenkins.getInstance().getDescriptor(DevCloudGlobalConfiguration.class);
        if (devcloudConfig != null) {
            return devcloudConfig.getInterval() * 1000L;
        }
        return PeriodicWork.MIN;
    }

    public long getInitialDelay() {
        return 15000L;
    }

    @Override
    protected void execute(TaskListener listener) throws IOException, InterruptedException {
        if (devcloudConfig == null) {
            devcloudConfig = (DevCloudGlobalConfiguration) Jenkins.getInstance().getDescriptor(DevCloudGlobalConfiguration.class);
            return;
        }
        try {
            JenkinsBeanSender sender = new JenkinsBeanSender(devcloudConfig);
            sender.send();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

}
