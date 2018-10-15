package com.huawei.devcloud.monitor.utils;

import java.util.logging.Logger;
import java.util.logging.Level;

public class IamJudge {
	
	private static final Logger logger = Logger.getLogger(IamJudge.class.getName());
	
	private String iamUrl;
	private String encodeUserName;
	private String encodePwd;
	
	
	public IamJudge(String iamUrl, String encodeUserName, String encodePwd) {
		this.iamUrl = iamUrl;
		this.encodeUserName = encodeUserName;
		this.encodePwd = encodePwd;
	}
	
	public String getCommonToken() throws AuthException {
		
		String[] info = CacheService.getInstance().getCommonInfo(iamUrl,encodeUserName,
				encodePwd);
		if(info == null || info.length == 0){
			logger.log(Level.WARNING,"[build-notification] getCommonToken result info is empty");
			return "";
		}
		String token = info[0];
		if (CommonUtils.isNullOrEmpty(token)) {
			logger.log(Level.WARNING,"[build-notification] getCommonToken token is empty");
		}
		return token;
	}
	
	
	private static boolean checkEmpty(String[] strs) {
		for (String s : strs) {
			if ((s == null) || ("".equals(s.trim()))) {
				return true;
			}
	    }
	    return false;
	}
	
	
}
