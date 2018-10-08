package com.huawei.devcloud.monitor.model;

import java.util.ArrayList;


public class LoadInfoB implements Load {
	
	public LoadInfoB(double compositeLoad, CpuMem cpumem, JobQueue jobQueue, HttpCountInfo httpCount, ThreadCountInfo threadCount, ArrayList<SlaveInfo> slaveInfo) {
        this.composite_load = compositeLoad;
        this.cpumem = cpumem;
        this.job_queue = jobQueue;
        this.http_count = httpCount;
        this.thread_count = threadCount;
        this.slave_info = slaveInfo;
	}
	
	private double composite_load = 0;
	private CpuMem cpumem;
	private JobQueue job_queue;
	private HttpCountInfo http_count;
	private ThreadCountInfo thread_count;
	private ArrayList<SlaveInfo> slave_info;
	public double getComposite_load() {
		return composite_load;
	}
	public void setComposite_load(double composite_load) {
		this.composite_load = composite_load;
	}
	public CpuMem getCpumem() {
		return cpumem;
	}
	public void setCpumem(CpuMem cpumem) {
		this.cpumem = cpumem;
	}
	public JobQueue getJob_queue() {
		return job_queue;
	}
	public void setJob_queue(JobQueue job_queue) {
		this.job_queue = job_queue;
	}
	public HttpCountInfo getHttp_count() {
		return http_count;
	}
	public void setHttp_count(HttpCountInfo http_count) {
		this.http_count = http_count;
	}
	public ThreadCountInfo getThread_count() {
		return thread_count;
	}
	public void setThread_count(ThreadCountInfo thread_count) {
		this.thread_count = thread_count;
	}
	public ArrayList<SlaveInfo> getSlave_info() {
		return slave_info;
	}
	public void setSlave_info(ArrayList<SlaveInfo> slave_info) {
		this.slave_info = slave_info;
	}
	@Override
	public String toString() {
		return "LoadInfoAksk [composite_load=" + composite_load + ", cpumem=" + cpumem + ", job_queue=" + job_queue
				+ ", http_count=" + http_count + ", thread_count=" + thread_count + ", slave_info=" + slave_info + "]";
	}
}
