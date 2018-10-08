package com.huawei.devcloud.monitor.model;

public class HttpResponse
{
    /**
     * 请求响应状态
     */
    private int responseStatus;
    
    /**
     * 响应内容
     */
    private String responseContent;

    public int getResponseStatus()
    {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus)
    {
        this.responseStatus = responseStatus;
    }

    public String getResponseContent()
    {
        return responseContent;
    }

    public void setResponseContent(String responseContent)
    {
        this.responseContent = responseContent;
    }
    
}

