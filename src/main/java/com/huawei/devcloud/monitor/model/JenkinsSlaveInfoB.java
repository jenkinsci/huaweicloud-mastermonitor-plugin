package com.huawei.devcloud.monitor.model;

import hudson.model.Computer;
import hudson.model.Executor;

import java.util.Iterator;

public class JenkinsSlaveInfoB extends SlaveInfo{
	
	private String display_name;
	private boolean is_online; 
	private String node_label;
	private String node_type;
	private int executor_num;
	private int idle_num;
	private ResourceConsumption cpu;
	private ResourceConsumption mem;
	private ResourceConsumption disk;
	private ResourceConsumption swap;

	public JenkinsSlaveInfoB(Computer computer) {
		super(computer);
	}

	@Override
	public void loadSlaveInfo() {
		String displayName = computer.getDisplayName().trim();
		setDisplay_name(displayName);
		setNode_label(computer.getNode().getLabelString());
		setExecutor_num(executors.size());
		int slaveIdle = 0;
		if (computer.isOnline()) {
			setIs_online(true);
			Iterator iterator = executors.iterator();
			while (iterator.hasNext()) {
				Executor executor = (Executor) iterator.next();
				if (executor.isIdle()) {
					slaveIdle ++;
				}
			}
			setIdle_num(slaveIdle);
		}else{
			setIs_online(false);
		}
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public boolean isIs_online() {
		return is_online;
	}

	public void setIs_online(boolean is_online) {
		this.is_online = is_online;
	}

	public String getNode_label() {
		return node_label;
	}

	public void setNode_label(String node_label) {
		this.node_label = node_label;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}

	public int getExecutor_num() {
		return executor_num;
	}

	public void setExecutor_num(int executor_num) {
		this.executor_num = executor_num;
	}

	public int getIdle_num() {
		return idle_num;
	}

	public void setIdle_num(int idle_num) {
		this.idle_num = idle_num;
	}

	public ResourceConsumption getCpu() {
		return cpu;
	}

	public void setCpu(ResourceConsumption cpu) {
		this.cpu = cpu;
	}

	public ResourceConsumption getMem() {
		return mem;
	}

	public void setMem(ResourceConsumption mem) {
		this.mem = mem;
	}

	public ResourceConsumption getDisk() {
		return disk;
	}

	public void setDisk(ResourceConsumption disk) {
		this.disk = disk;
	}

	public ResourceConsumption getSwap() {
		return swap;
	}

	public void setSwap(ResourceConsumption swap) {
		this.swap = swap;
	}

	@Override
	public String toString() {
		return "JenkinsSlaveInfoAkSk [display_name=" + display_name + ", is_online=" + is_online + ", node_label="
				+ node_label + ", node_type=" + node_type + ", executor_num=" + executor_num + ", idle_num=" + idle_num
				+ "]";
	}
	
}
