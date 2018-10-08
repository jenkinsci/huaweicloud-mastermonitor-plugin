package com.huawei.devcloud.monitor.basic;

import com.huawei.devcloud.monitor.model.JenkinsInfo;
import com.huawei.devcloud.monitor.model.JobQueue;
import com.huawei.devcloud.monitor.model.JobQueueBean;
import jenkins.model.Jenkins;

public class DefaultJenkinsInfo extends JenkinsInfo {

    public DefaultJenkinsInfo(Jenkins jenkins) {
        super(jenkins);
    }

    @Override
    public JobQueue getJobQueue(Integer totalJobs, Integer runningJobs, Integer queueJobs) {
        return new JobQueueBean(totalJobs, runningJobs, queueJobs);
    }
}
