package com.huawei.devcloud.monitor.model;


public class HttpCountB implements HttpCountInfo{
    private int all_http_count = 0;

    private int http_error_count = 0;
    
	public HttpCountB(int all_http_count, int http_error_count) {
		super();
		this.all_http_count = all_http_count;
		this.http_error_count = http_error_count;
	}

	public int getAll_http_count() {
		return all_http_count;
	}

	public void setAll_http_count(int all_http_count) {
		this.all_http_count = all_http_count;
	}

	public int getHttp_error_count() {
		return http_error_count;
	}

	public void setHttp_error_count(int http_error_count) {
		this.http_error_count = http_error_count;
	}

	@Override
	public String toString() {
		return "HttpCountAksk [all_http_count=" + all_http_count + ", http_error_count=" + http_error_count + "]";
	}

}
