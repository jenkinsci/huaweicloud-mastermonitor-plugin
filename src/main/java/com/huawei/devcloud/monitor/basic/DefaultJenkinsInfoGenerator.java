package com.huawei.devcloud.monitor.basic;

import java.util.ArrayList;

import com.huawei.devcloud.monitor.AbstractJenkinsInfoGenerator;
import com.huawei.devcloud.monitor.model.AccessInfo;
import com.huawei.devcloud.monitor.model.JenkinsBean;
import com.huawei.devcloud.monitor.model.JenkinsSlaveInfo;
import com.huawei.devcloud.monitor.model.LoadInfo;
import com.huawei.devcloud.monitor.model.SlaveInfo;
import com.huawei.devcloud.monitor.utils.LoadInfoHelper;

import hudson.model.Computer;

public class DefaultJenkinsInfoGenerator extends AbstractJenkinsInfoGenerator {
    public DefaultJenkinsInfoGenerator() {
        super();
        systemInfo = new DefaultSystemInfo(javaInformations);
        jenkinsInfo = new DefaultJenkinsInfo(jenkins);
    }

    @Override
    public void generateLoadInfo(JenkinsBean jenkinsBean) {
        ArrayList<SlaveInfo> slaveInfo = new ArrayList<SlaveInfo>();
        Computer[] computers = jenkins.getComputers();
        for (Computer computer : computers) {
            SlaveInfo slave = new JenkinsSlaveInfo(computer);
            slave.loadInfo();
            slaveInfo.add(slave);
        }
        LoadInfo loadInfo = new LoadInfo(javaInformations.getSystemLoadAverage(),
                LoadInfoHelper.getCpuMemDiskInfo(javaInformations), jenkinsInfo.getJobQueue(), systemInfo.getHttpRequestCount(), systemInfo.getThreadCount(), slaveInfo);
        jenkinsBean.setLoadInfo(loadInfo);
    }

    @Override
    protected JenkinsBean getJenkinsBean() {
        JenkinsBean jenkinsBean = super.getJenkinsBean();
        //优先使用root用户的token,不存在root用户时使用jenkins用户
        AccessInfo accessInfo = new AccessInfo("root");
        if (AccessInfo.TOKEN_HIDDEN.equals(accessInfo.getToken())) {
            accessInfo = new AccessInfo("jenkins");
        }
        jenkinsBean.setAccessInfo(accessInfo);
        return jenkinsBean;
    }
}
