package com.huawei.devcloud.monitor.remote;

import java.util.ArrayList;

import com.huawei.devcloud.monitor.AbstractJenkinsInfoGenerator;
import com.huawei.devcloud.monitor.model.JenkinsBean;
import com.huawei.devcloud.monitor.model.JenkinsSlaveInfoB;
import com.huawei.devcloud.monitor.model.LoadInfoB;
import com.huawei.devcloud.monitor.model.SlaveInfo;
import com.huawei.devcloud.monitor.utils.LoadInfoHelper;

import hudson.model.Computer;

public class RemoteJenkinsInfoGenerator extends AbstractJenkinsInfoGenerator {

    public RemoteJenkinsInfoGenerator() {
        super();
        systemInfo = new RemoteSystemInfo(javaInformations);
        jenkinsInfo = new RemoteJenkinsInfo(jenkins);
    }

    @Override
    public void generateLoadInfo(JenkinsBean jenkinsBean) {
        ArrayList<SlaveInfo> slaveInfo = new ArrayList<SlaveInfo>();
        Computer[] computers = jenkins.getComputers();
        for (Computer computer : computers) {
            SlaveInfo slave = new JenkinsSlaveInfoB(computer);
            slave.loadInfo();
            slaveInfo.add(slave);
        }
        LoadInfoB loadInfo = new LoadInfoB(javaInformations.getSystemLoadAverage(),
                LoadInfoHelper.getCpuMemDiskInfo(javaInformations), jenkinsInfo.getJobQueue(), systemInfo.getHttpRequestCount(), systemInfo.getThreadCount(), slaveInfo);
        jenkinsBean.setLoad_info(loadInfo);
    }
}
