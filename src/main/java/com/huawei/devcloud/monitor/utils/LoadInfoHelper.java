package com.huawei.devcloud.monitor.utils;

import static com.huawei.devcloud.monitor.model.Constants.B_TO_GB;
import static com.huawei.devcloud.monitor.model.Constants.B_TO_MB;
import static com.huawei.devcloud.monitor.model.Constants.decimalFormat;

import com.huawei.devcloud.monitor.model.CpuMem;
import com.huawei.devcloud.monitor.model.ResourceConsumption;

import net.bull.javamelody.internal.model.JavaInformations;
import net.bull.javamelody.internal.model.MemoryInformations;

public class LoadInfoHelper {
 
    public static CpuMem getCpuMemDiskInfo(JavaInformations javaInformations){
        if(javaInformations==null){
            return new CpuMem(null,null,null);
        }
        MemoryInformations memoryInformations = javaInformations.getMemoryInformations();
        //numberFormat.setMaximumFractionDigits(2);
        float CPULoad = Float.parseFloat(decimalFormat.format(javaInformations.getSystemCpuLoad()));
        float totalMem = Float.parseFloat(decimalFormat.format((float) (memoryInformations.
                getMaxMemory()) / B_TO_MB));
        //the aim of the above code is to make the unit of totalMem "Mb"
        float usedMem = Float.parseFloat(decimalFormat.format((float) (memoryInformations.
                getUsedMemory()) / B_TO_MB));
        //the aim of the above code is to make the unit of usedMem "Mb"
        float emptyMem = Float.parseFloat(decimalFormat.format(totalMem - usedMem));
        float emptyDisk = Float.parseFloat(decimalFormat.format((float) (javaInformations.
                getFreeDiskSpaceInTemp()) / B_TO_GB));
        ResourceConsumption cpuConsumption = new ResourceConsumption(-1, CPULoad, -1);
        ResourceConsumption memConsumption = new ResourceConsumption(totalMem, usedMem, emptyMem);
        ResourceConsumption diskConsumption = new ResourceConsumption(-1, -1, emptyDisk);
        CpuMem cpuMemDiskBean = new CpuMem(cpuConsumption, memConsumption, diskConsumption);
        return cpuMemDiskBean;
    }

}
