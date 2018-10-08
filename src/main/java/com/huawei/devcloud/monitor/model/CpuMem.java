package com.huawei.devcloud.monitor.model;

public class CpuMem {

    public CpuMem(ResourceConsumption cpu, ResourceConsumption mem, ResourceConsumption disk) {
        this.cpu = cpu;
        this.mem = mem;
        this.disk = disk;
    }
    private ResourceConsumption cpu = null;
    private ResourceConsumption mem = null;
    private ResourceConsumption disk = null;

    public ResourceConsumption getCpu() {
        return cpu;
    }

    public void setCpu(ResourceConsumption cpu) {
        this.cpu = cpu;
    }

    public ResourceConsumption getMem() {
        return mem;
    }

    public void setMem(ResourceConsumption mem) {
        this.mem = mem;
    }

    public ResourceConsumption getDisk() {
        return disk;
    }

    public void setDisk(ResourceConsumption disk) {
        this.disk = disk;
    }

    @Override
    public String toString() {
        return "CPUMemDiskBean{" +
                "cpu=" + cpu +
                ", mem=" + mem +
                ", disk=" + disk +
                '}';
    }

    @Override
    public int hashCode() {
        int result = cpu != null ? cpu.hashCode() : 0;
        result = 31 * result + (mem != null ? mem.hashCode() : 0);
        result = 31 * result + (disk != null ? disk.hashCode() : 0);
        return result;
    }


}
