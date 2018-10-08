package com.huawei.devcloud.monitor.model;


/**
 * 虚拟机规格
 **/
public class Vmformat {
    private int id = 0;
    private String os = null;
    private int cpu = 0;
    private int mem = 0;

    private float disk = 0;


    @Override
    public String toString() {
        return "Vmformat{" +
                "id=" + id +
                ", os='" + os + '\'' +
                ", cpu=" + cpu +
                ", mem=" + mem +
                ", disk=" + disk +
                '}';
    }

    /**
     * id
     **/

    public int getId()
    {
        return id;
    }
    public void setId(int id_in)
    {
        this.id = id_in;
    }

    /**
     * 操作系统
     **/

    public String getOs()
    {
        return os;
    }
    public void setOs(String os_in)
    {
        this.os = os_in;
    }

    /**
     * cpu总量
     *
     * @param vmformatCpu*/

    public int getCpu(String vmformatCpu)
    {
        return cpu;
    }
    public void setCpu(int cpu_in)
    {
        this.cpu = cpu_in;
    }

    /**
     * 内存总量（M）
     **/

    public int getMem()
    {
        return mem;
    }
    public void setMem(int mem_in)
    {
        this.mem = mem_in;
    }

    /**
     * 硬盘总量（G）
     **/

    public float getDisk()
    {
        return disk;
    }
    public void setDisk(float disk_in)
    {
        this.disk = disk_in;
    }
}
