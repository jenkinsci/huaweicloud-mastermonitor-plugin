package com.huawei.devcloud.monitor.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import com.google.gson.JsonObject;

import hudson.util.Secret;

public class CommonUtils {

	private static final Logger logger = Logger.getLogger(CommonUtils.class.getName());
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	public static boolean isNullOrEmpty(Object obj) {
		return obj==null || String.valueOf(obj).trim().isEmpty();
	}

	public static String replaceNull(String str) {
		return isNullOrEmpty(str) ? "" : str;
	}
	
	public static boolean isNullOrEmptyList(List<?> list) {
		if (list != null && !list.isEmpty()) {
			return false;
		}
		return true;
	}

	public static <T> boolean isNullOrEmptyArray(T[] array) {
		if (array != null && array.length > 0) {
			return false;
		}
		return true;
	}

	public static boolean isNullOrEmptyMap(Map<?, ?> map) {
		if (map == null || map.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static List<String> stringToList(String str, String quote) {
		if(isNullOrEmpty(str) || isNullOrEmpty(quote)) {
			return null;
		}
		String[] arr = str.split(quote);
		if(isNullOrEmptyArray(arr)) {
			return null;
		}
		return Arrays.asList(arr);
	}
	
	/**
	 * 将date转换为utc格式的字符串
	 * @param date
	 * @return
	 */
	public static String formatDateToUtcStr(Date date) {
		if(CommonUtils.isNullOrEmpty(date)) {
			return null;
		}
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df2.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		return df2.format(date);
	}
	
	/**
	 * 将utc格式的字符串转为date
	 * @param utc
	 * @param dayAdd1 是否加一天
	 * @return
	 */
	public static Date formatUtcStringToDate(String utc, boolean dayAdd1) {
		if(CommonUtils.isNullOrEmpty(utc)) {
			return null;
		}
		int onyDayLength = "yyyy-MM-dd".length();
		// 如果传入的字符串不含时分秒，则判断转换结果是否需要加一天
		if(utc.length() == onyDayLength) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			try {
				Date date = sdf.parse(utc);
				if(dayAdd1) {
					Calendar cal=Calendar.getInstance(); 
					cal.setTime(date);
					cal.add(cal.DATE, 1);
					date=cal.getTime();  
				}
				return date;
			} catch (ParseException e) {
				logger.log(Level.WARNING,"[build-notification] format date faild:" + utc);
			}
		}else {
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			df2.setTimeZone(TimeZone.getTimeZone("GMT+0"));
			try {
				Date d = df2.parse(utc);
				return d;
			} catch (ParseException e) {
				logger.log(Level.WARNING,"[build-notification] format utcdate faild:" + utc);
			}
		}
		return null;
	}
	
	public static String formatStandardDate(Date longDate) {
		if (longDate == null)
			return "";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(longDate);
	}
	
	public static Long calculateDateInterval(Date start, Date end) {
		if(CommonUtils.isNullOrEmpty(start) || CommonUtils.isNullOrEmpty(end)) {
			return null;
		}
		return end.getTime() - start.getTime();
	}
	
	public static String formatLongDate(Date date) {
		if(null == date) {
			date = new Date();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}
	
	public static boolean jsonValueNullOrEmpty(JsonObject json, String key) {
		return !(json.has(key) && !json.get(key).isJsonNull());
	}
	
	public static String formatWithquote(String[] array,String quote){
		if(array==null || array.length==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(String str:array){
			if(isNullOrEmpty(str)) {
				continue;
			}
			sb.append(str + quote);
		}
		sb.delete(sb.lastIndexOf(quote), sb.length());
		return sb.toString();
	}
	
	public static String getPrefix(String... prefix) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (null != prefix && 0 != prefix.length) {
			for (int i = 0; i < prefix.length; i++) {
				sb.append(prefix[i]);
				sb.append(";");
			}
		}
		if(sb.length() > 1){
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("] ");
		return sb.toString();
	}
	public static String encodeByJenkins(String key){
		if(StringUtils.isBlank(key))
		{
			return key;
		}
		Secret sec = Secret.fromString(key);
        return sec.getEncryptedValue();
	}
	public static String decodeByJenkins(String key){
		Secret sec = Secret.fromString(key);
        return sec.getPlainText();
	}
	public static String replaceIp(String str) {
		String reg = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)(:\\d{0,5})?";
		return str.replaceAll(reg, "******");
	}
}
