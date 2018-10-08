package com.huawei.devcloud.monitor.utils;

import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CacheService {
	private static final Logger logger = Logger.getLogger(CacheService.class.getName());
	
	private static CacheService service = new CacheService();
	private String userName;
	private String pwd;
	private String token;
	private Date updateTime = new Date();
	
	public static final String OP_SERVICE_BODY = 
			"{" +
			"	    \"auth\": {" +
			"	        \"identity\": {" +
			"	            \"methods\": [" +
			"	                \"password\"" +
			"	            ]," +
			"	            \"password\": {" +
			"	                \"user\": {" +
			"	                    \"name\": \"%s\"," +
			"	                    \"password\": \"%s\"," +
			"	                    \"domain\": {" +
			"	                        \"name\": \"op_service\"" +
			"	                    }" +
			"	                }" +
			"	            }" +
			"	        }," +
			"	        \"scope\": {" +
			"	            \"domain\": {" +
			"	                \"name\": \"op_service\"" +
			"	            }" +
			"	        }" +
			"	    }" +
			"	}" ;
			
	//30s
	private long timeOut = 10800000;
	private CacheService(){
		
	}
	
	public static CacheService getInstance(){
		return service;
	}
	
	public synchronized String[] getCommonInfo(String iamUrl,String encodeUser,
			String encodePwd){
		if(isValid() && !CommonUtils.isNullOrEmpty(userName)
				&& !CommonUtils.isNullOrEmpty(pwd)
				&& !CommonUtils.isNullOrEmpty(token)){
			return new String[]{token,userName,pwd}; 
		}
		updateTime = new Date();
		userName = getCommonUser(encodeUser);
		pwd = getCommonPwd(encodePwd);
		token = getCommonToken(iamUrl);
		return new String[]{token,userName,pwd}; 
	}
	
	private String getCommonToken(String iamUrl){
		String token = null;
		String strBody = String
				.format(OP_SERVICE_BODY, 
						userName, 
						pwd);
		String certServerUrl = iamUrl + "/v3/auth/tokens";
		ReqestClientUtil requestUtil = new ReqestClientUtil();
		token = requestUtil.loadIAMToken(certServerUrl, strBody,"");
		if (CommonUtils.isNullOrEmpty(token)) {
			logger.log(Level.WARNING,"[build-notification] CacheService token is empty!");
		}
		return token;
	}
	
	private String getCommonUser(String encodeUser){
		return CommonUtils.decodeByJenkins(encodeUser);
	}
	
	private String getCommonPwd(String encodePwd){
		return CommonUtils.decodeByJenkins(encodePwd);
	}
	
	private boolean isValid(){
		 logger.log(Level.INFO,"[build-notification] CacheService token isValid");
		 return (new Date().getTime() - updateTime.getTime()) < timeOut;
	}
	
}
