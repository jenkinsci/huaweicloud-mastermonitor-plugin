package com.huawei.devcloud.monitor.model;

import hudson.model.AbstractBuild;
import net.bull.javamelody.internal.model.ProcessInformations;

import java.util.List;


public class GainCPU {
    private final List<ProcessInformations> processInformationsList;

    public GainCPU(List<ProcessInformations> processInformationsList) {
        this.processInformationsList = processInformationsList;
    }
    private float gainCPU(ProcessInformations processInformations,AbstractBuild build){
        float CPUPercentage = processInformations.getCpuPercentage();

        return CPUPercentage;
    }
}
