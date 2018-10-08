package com.huawei.devcloud.monitor.remote;

import com.huawei.devcloud.monitor.model.JenkinsInfo;
import com.huawei.devcloud.monitor.model.JobQueue;
import com.huawei.devcloud.monitor.model.JobQueueBeanB;
import jenkins.model.Jenkins;

public class RemoteJenkinsInfo extends JenkinsInfo {

    public RemoteJenkinsInfo(Jenkins jenkins) {
        super(jenkins);
    }

    @Override
    public JobQueue getJobQueue(Integer totalJobs, Integer runningJobs, Integer queueJobs) {
        return new JobQueueBeanB(totalJobs, runningJobs, queueJobs);
    }
}
