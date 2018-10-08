package com.huawei.devcloud.monitor.model;

import hudson.model.Computer;
import hudson.model.Executor;
import hudson.node_monitors.DiskSpaceMonitor;
import hudson.node_monitors.DiskSpaceMonitorDescriptor;
import hudson.node_monitors.NodeMonitor;
import hudson.node_monitors.SwapSpaceMonitor;
import org.jvnet.hudson.MemoryUsage;

import java.util.List;
import static com.huawei.devcloud.monitor.model.Constants.*;

public abstract class SlaveInfo {
    
    protected transient Computer computer;
    protected transient List<Executor> executors;

    public SlaveInfo(Computer computer) {
        this.computer = computer;
        this.executors = computer.getExecutors();
    }

    public void loadInfo() {
        SwapSpaceMonitor swapSpaceMonitor = null;
        DiskSpaceMonitor diskSpaceMonitor = null;
        for (NodeMonitor monitor : NodeMonitor.getAll()) {
            if (monitor instanceof SwapSpaceMonitor) {
                swapSpaceMonitor = (SwapSpaceMonitor) monitor;
            } else if (monitor instanceof DiskSpaceMonitor) {
                diskSpaceMonitor = (DiskSpaceMonitor) monitor;
            }
        }
        MemoryUsage memoryUsage = swapSpaceMonitor != null ? (MemoryUsage) swapSpaceMonitor.data(computer) : null;
        DiskSpaceMonitorDescriptor.DiskSpace diskSpace = swapSpaceMonitor != null ? (DiskSpaceMonitorDescriptor.DiskSpace) diskSpaceMonitor.data(computer) : null;
        float slaveTotalMem = memoryUsage == null ? -1 : Float.parseFloat(decimalFormat.format((float) (memoryUsage.totalPhysicalMemory) / B_TO_MB));
        float slaveEmptyMem = memoryUsage == null ? -1 : Float.parseFloat(decimalFormat.format((float) (memoryUsage.availablePhysicalMemory) / B_TO_MB));
        float slaveUsedMem = memoryUsage == null ? -1 : Float.parseFloat(decimalFormat.format(slaveTotalMem - slaveEmptyMem));
        float slaveEmptyDisk = diskSpace == null ? -1 : Float.parseFloat(decimalFormat.format((float) diskSpace.size / B_TO_GB));
        float slaveTotalSwap = memoryUsage == null ? -1 : Float.parseFloat(decimalFormat.format((float) (memoryUsage.totalSwapSpace) / B_TO_MB));
        float slaveEmptySwap = memoryUsage == null ? -1 : Float.parseFloat(decimalFormat.format((float) (memoryUsage.availableSwapSpace) / B_TO_MB));
        float slaveUsedSwap = memoryUsage == null ? -1 : Float.parseFloat(decimalFormat.format(slaveTotalSwap - slaveEmptySwap));
        ResourceConsumption slaveCpuConsumption = new ResourceConsumption(-1, -1, -1);
        ResourceConsumption slaveMemConsumption = new ResourceConsumption(slaveTotalMem, slaveUsedMem, slaveEmptyMem);
        ResourceConsumption slaveDiskConsumption = new ResourceConsumption(-1, -1, slaveEmptyDisk);
        ResourceConsumption slaveSwapConsumption = new ResourceConsumption(slaveTotalSwap, slaveUsedSwap, slaveEmptySwap);
        setCpu(slaveCpuConsumption);
        setMem(slaveMemConsumption);
        setDisk(slaveDiskConsumption);
        setSwap(slaveSwapConsumption);
        loadSlaveInfo();
    }

    public abstract void loadSlaveInfo();

    public abstract void setCpu(ResourceConsumption cpu);

    public abstract void setMem(ResourceConsumption mem);

    public abstract void setDisk(ResourceConsumption disk);

    public abstract void setSwap(ResourceConsumption swap);

}
