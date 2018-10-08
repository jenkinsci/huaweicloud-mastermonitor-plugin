package com.huawei.devcloud.monitor.model;


public class JenkinsBean extends BasicInfoBean{

    private LoadInfo loadInfo;
    private LoadInfoB load_info;
    
    public LoadInfo getLoadInfo() {
        return loadInfo;
    }
    public void setLoadInfo(LoadInfo loadInfo) {
        this.loadInfo = loadInfo;
    }
    public LoadInfoB getLoad_info() {
		return load_info;
	}
	public void setLoad_info(LoadInfoB load_info) {
		this.load_info = load_info;
	}
	
	@Override
	public String toString() {
		return "JenkinsBean [loadInfo=" + loadInfo + ", load_info=" + load_info + "]";
	}
}
