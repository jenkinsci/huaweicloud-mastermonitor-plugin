package com.huawei.devcloud.monitor.model;


import java.io.Serializable;

public class JobBuildApi implements Serializable
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8584443502933308403L;

    private boolean building = false;
    
    private String description = null;
    
    private String displayName = null;
    
    private long duration = 0;
    
    private long estimatedDuration = 0;
    
    private JobBuildExecutor executor = null;
    
    private String fullDisplayName = null;
    
    private String id = null;
    
    private boolean keepLog = false;
    
    private int number = 0;
    
    private long queueId = -1;
    
    private String result = null;
    
    private long timestamp = 0;
    
    private String url = null;
    
    private String builtOn = null;

    public boolean isBuilding()
    {
        return building;
    }

    public void setBuilding(boolean building)
    {
        this.building = building;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public long getDuration()
    {
        return duration;
    }

    public void setDuration(long duration)
    {
        this.duration = duration;
    }

    public long getEstimatedDuration()
    {
        return estimatedDuration;
    }

    public void setEstimatedDuration(long estimatedDuration)
    {
        this.estimatedDuration = estimatedDuration;
    }

    public JobBuildExecutor getExecutor()
    {
        return executor;
    }

    public void setExecutor(JobBuildExecutor executor)
    {
        this.executor = executor;
    }

    public String getFullDisplayName()
    {
        return fullDisplayName;
    }

    public void setFullDisplayName(String fullDisplayName)
    {
        this.fullDisplayName = fullDisplayName;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public boolean isKeepLog()
    {
        return keepLog;
    }

    public void setKeepLog(boolean keepLog)
    {
        this.keepLog = keepLog;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getBuiltOn()
    {
        return builtOn;
    }

    public void setBuiltOn(String builtOn)
    {
        this.builtOn = builtOn;
    }

    public long getQueueId()
    {
        return queueId;
    }

    public void setQueueId(long queueId)
    {
        this.queueId = queueId;
    }
    
}