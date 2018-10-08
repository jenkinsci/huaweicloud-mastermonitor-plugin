package com.huawei.devcloud.monitor.model;

public interface Request {
	
	public static int CONNECT_TIMEOUT = 5000;

    public static int SOCKET_TIMEOUT = 120000;
    
	public int socketTimeOut = SOCKET_TIMEOUT;
	
	public static int getSocketTimeOut() {
	       return socketTimeOut;
	}

}
