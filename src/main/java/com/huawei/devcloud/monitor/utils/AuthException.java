package com.huawei.devcloud.monitor.utils;

public class AuthException extends BaseException {
	private static final long serialVersionUID = 67960164130637149L;
	public AuthException(String errorCode, String message) {
		super(errorCode,message);
	}
	public AuthException(String errorCode) {
		super(errorCode);
	}
}
