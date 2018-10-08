package com.huawei.devcloud.monitor.model;

public class ThreadCountB implements ThreadCountInfo{
	
    private long all_thread_count = 0;

    private int active_thread_count = 0;
    
	public ThreadCountB(long all_thread_count, int active_thread_count) {
		super();
		this.all_thread_count = all_thread_count;
		this.active_thread_count = active_thread_count;
	}

	public long getAll_thread_count() {
		return all_thread_count;
	}

	public void setAll_thread_count(long all_thread_count) {
		this.all_thread_count = all_thread_count;
	}

	public int getActive_thread_count() {
		return active_thread_count;
	}

	public void setActive_thread_count(int active_thread_count) {
		this.active_thread_count = active_thread_count;
	}

	@Override
	public String toString() {
		return "ThreadCountAksk [all_thread_count=" + all_thread_count + ", active_thread_count=" + active_thread_count
				+ "]";
	}
    
}
