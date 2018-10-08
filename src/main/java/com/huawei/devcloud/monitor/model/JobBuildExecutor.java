package com.huawei.devcloud.monitor.model;

import java.io.Serializable;

public class JobBuildExecutor implements Serializable
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 16068867614016544L;

    private boolean idle = false;
    
    private boolean likelyStuck = false;
    
    private int number = 0;
    
    private int progress = 0;
    
    public JobBuildExecutor()
    {
        super();
    } 
    
    /**
    **/

    public boolean getIdle() 
    {
        return idle;
    }
    public void setIdle(boolean idle_in) 
    {
        this.idle = idle_in;
    }
    
    /**
    **/

    public boolean getLikelyStuck() 
    {
        return likelyStuck;
    }
    public void setLikelyStuck(boolean likelyStuck_in) 
    {
        this.likelyStuck = likelyStuck_in;
    }
    
    /**
    **/

    public int getNumber() 
    {
        return number;
    }
    public void setNumber(int number_in) 
    {
        this.number = number_in;
    }
    
    /**
    **/

    public int getProgress() 
    {
        return progress;
    }
    public void setProgress(int progress_in) 
    {
        this.progress = progress_in;
    }
    
      @Override
      public String toString()  {
        StringBuilder sb = new StringBuilder();
        sb.append("class JobBuildExecutor {\n");
        sb.append("  idle: ").append(idle).append("\n");
        sb.append("  likelyStuck: ").append(likelyStuck).append("\n");
        sb.append("  number: ").append(number).append("\n");
        sb.append("  progress: ").append(progress).append("\n");
        sb.append("}\n");
        return sb.toString();
      }
}