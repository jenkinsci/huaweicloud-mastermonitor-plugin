package com.huawei.devcloud.monitor.model;

public class JenkinsBuilfInfo
{
    private String jenkinsUrl = null;
    
    private String jobName = null;
    
    private JobBuildApi jobBuildApi = null;

    public String getJenkinsUrl()
    {
        return jenkinsUrl;
    }

    public void setJenkinsUrl(String jenkinsUrl)
    {
        this.jenkinsUrl = jenkinsUrl;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public JobBuildApi getJobBuildApi()
    {
        return jobBuildApi;
    }

    public void setJobBuildApi(JobBuildApi jobBuildApi)
    {
        this.jobBuildApi = jobBuildApi;
    }
}
