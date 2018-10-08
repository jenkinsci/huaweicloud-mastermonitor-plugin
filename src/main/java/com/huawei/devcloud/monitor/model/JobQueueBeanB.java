package com.huawei.devcloud.monitor.model;

public class JobQueueBeanB implements JobQueue{

	public JobQueueBeanB(Integer total_jobs, Integer running_jobs, Integer queue_jobs) {
		super();
		this.total_jobs = total_jobs;
		this.running_jobs = running_jobs;
		this.queue_jobs = queue_jobs;
	}

	private Integer total_jobs = null;

    private Integer running_jobs = null;

    private Integer queue_jobs = null;

	public Integer getTotal_jobs() {
		return total_jobs;
	}

	public void setTotal_jobs(Integer total_jobs) {
		this.total_jobs = total_jobs;
	}

	public Integer getRunning_jobs() {
		return running_jobs;
	}

	public void setRunning_jobs(Integer running_jobs) {
		this.running_jobs = running_jobs;
	}

	public Integer getQueue_jobs() {
		return queue_jobs;
	}

	public void setQueue_jobs(Integer queue_jobs) {
		this.queue_jobs = queue_jobs;
	}

	@Override
	public String toString() {
		return "JobQueueBeanAksk [total_jobs=" + total_jobs + ", running_jobs=" + running_jobs + ", queue_jobs="
				+ queue_jobs + "]";
	}
  
}
