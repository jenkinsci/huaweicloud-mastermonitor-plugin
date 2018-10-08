package com.huawei.devcloud.monitor.model;


public class JobQueueBean implements JobQueue{
    public JobQueueBean(Integer totalJobs, Integer runningJobs, Integer queueJobs) {
        this.totalJobs = totalJobs;
        this.runningJobs = runningJobs;
        this.queueJobs = queueJobs;
    }


    private Integer totalJobs = null;

    private Integer runningJobs = null;

    private Integer queueJobs = null;

    public Integer getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(Integer totalJobs) {
        this.totalJobs = totalJobs;
    }

    public Integer getRunningJobs() {
        return runningJobs;
    }

    public void setRunningJobs(Integer runningJobs) {
        this.runningJobs = runningJobs;
    }

    public Integer getQueueJobs() {
        return queueJobs;
    }

    public void setQueueJobs(Integer queueJobs) {
        this.queueJobs = queueJobs;
    }

    @Override
    public String toString() {
        return "JobQueueBean{" +
                "totalJobs=" + totalJobs +
                ", runningJobs=" + runningJobs +
                ", queueJobs=" + queueJobs +
                '}';
    }
}
