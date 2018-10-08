package com.huawei.devcloud.monitor.model;

import hudson.model.Computer;
import hudson.model.Executor;

import java.util.Iterator;
import java.util.List;

public class JenkinsSlaveInfo extends SlaveInfo{
	private String displayName;
	private boolean isOnline; 
	private String nodeLabel;
	private String nodeType;
	private int executorNum;
	private int idleNum;
	private ResourceConsumption cpu;
	private ResourceConsumption mem;
	private ResourceConsumption disk;
	private ResourceConsumption swap;

	public JenkinsSlaveInfo(Computer computer) {
		super(computer);
	}

	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int getExecutorNum() {
		return executorNum;
	}
	public void setExecutorNum(int executorNum) {
		this.executorNum = executorNum;
	}
	public int getIdleNum() {
		return idleNum;
	}
	public void setIdleNum(int idleNum) {
		this.idleNum = idleNum;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getNodeLabel() {
		return nodeLabel;
	}
	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
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


	public void loadSlaveInfo() {
		String displayName = computer.getDisplayName().trim();
		setDisplayName(displayName);
		setNodeLabel(computer.getNode().getLabelString());
		setExecutorNum(executors.size());
		int slaveIdle = 0;
		if (computer.isOnline()) {
			setOnline(true);
			Iterator iterator = executors.iterator();
			while (iterator.hasNext()) {
				Executor executor = (Executor) iterator.next();
				if (executor.isIdle()) {
					slaveIdle ++;
				}
			}
			setIdleNum(slaveIdle);
		}else{
			setOnline(false);
		}
	}

	@Override
	public String toString() {
		return "JenkinsSlaveInfo [displayName=" + displayName + ", isOnline=" + isOnline + ", nodeLabel=" + nodeLabel
				+ ", nodeType=" + nodeType + ", executorNum=" + executorNum + ", idleNum=" + idleNum + ", cpu=" + cpu
				+ ", mem=" + mem + ", disk=" + disk + ", swap=" + swap + "]";
	}
}
