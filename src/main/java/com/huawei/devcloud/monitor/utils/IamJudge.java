package com.huawei.devcloud.monitor.utils;

import java.util.logging.Logger;
import java.util.logging.Level;

public class IamJudge {
	
	private static final Logger logger = Logger.getLogger(IamJudge.class.getName());
	
	private String iamUrl;
	private String encodeUserName;
	private String encodePwd;
	
	public static final String TENANT_BODY = 
			"{" +
			"	    \"auth\": {" +
			"	        \"identity\": {" +
			"	            \"methods\": [" +
			"	                \"hw_assume_role\"" +
			"	            ]," +
			"	            \"hw_assume_role\": {" +
			"	                \"domain_id\": \"%s\"," +
			"	                \"xrole_name\": \"op_service\"" +
			"	            }" +
			"	        }," +
			"	        \"scope\": {" +
			"	            \"domain\": {" +
			"	                \"id\": \"%s\"" +
			"	            }" +
			"	        }" +
			"	    }" +
			"	}" ;
	
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
	
	public String loadTenantToken(String domanId) {
		try {
			String pkiToken = getCommonToken();
			String certServerUrl = this.iamUrl + "/v3/auth/tokens";
			if (checkEmpty(new String[] { domanId, pkiToken, iamUrl })) {
				logger.log(Level.WARNING,"[build-notification] null para for token request.");
			    return null;
	    	}
		    String strBody = String.format(TENANT_BODY, 
		    		domanId, domanId);
		    ReqestClientUtil requestUtil = new ReqestClientUtil();
		    String token = requestUtil.loadIAMToken(certServerUrl, strBody, pkiToken);
			if (CommonUtils.isNullOrEmpty(token)) {
				logger.log(Level.WARNING,"[build-notification] token isEmpty.");
			}
			return token;
		} catch (AuthException e) {
			logger.log(Level.SEVERE,"[build-notification] invalid common username and pwd.");
		}
		return null;
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
