package com.huawei.devcloud.monitor.model;

import java.util.ArrayList;



public class LoadInfo implements Load{

    public LoadInfo(double compositeLoad, CpuMem cpumem, JobQueue jobQueue, HttpCountInfo httpCount, ThreadCountInfo threadCount, ArrayList<SlaveInfo> slaveInfo) {
        this.compositeLoad = compositeLoad;
        this.cpumem = cpumem;
        this.jobQueue = jobQueue;
        this.httpCount = httpCount;
        this.threadCount = threadCount;
        this.slaveInfo = slaveInfo;
    }

    private double compositeLoad = 0;
    private CpuMem cpumem;
    private JobQueue jobQueue;
    private HttpCountInfo httpCount;
    private ThreadCountInfo threadCount;
    private ArrayList<SlaveInfo> slaveInfo;

    public double getCompositeLoad() {
        return compositeLoad;
    }

    public void setCompositeLoad(double compositeLoad) {
        this.compositeLoad = compositeLoad;
    }

    public CpuMem getCpumem() {
        return cpumem;
    }

    public void setCpumem(CpuMem cpumem) {
        this.cpumem = cpumem;
    }

    public JobQueue getJobQueue() {
        return jobQueue;
    }

    public void setJobQueue(JobQueue jobQueue) {
        this.jobQueue = jobQueue;
    }

    public HttpCountInfo getHttpCount() {
        return httpCount;
    }

    public void setHttpCount(HttpCount httpCount) {
        this.httpCount = httpCount;
    }

    public ThreadCountInfo getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(ThreadCount threadCount) {
        this.threadCount = threadCount;
    }

    public ArrayList<SlaveInfo> getSlaveInfo() {
		return slaveInfo;
	}

	public void setSlaveInfo(ArrayList<SlaveInfo> slaveInfo) {
		this.slaveInfo = slaveInfo;
	}

	@Override
	public String toString() {
		return "LoadInfo [compositeLoad=" + compositeLoad + ", cpumem=" + cpumem + ", jobQueue=" + jobQueue
				+ ", httpCount=" + httpCount + ", threadCount=" + threadCount + ", slaveInfo=" + slaveInfo + "]";
	}

}
