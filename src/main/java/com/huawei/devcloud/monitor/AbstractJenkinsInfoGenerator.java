package com.huawei.devcloud.monitor;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.huawei.devcloud.global_configuration.DevCloudGlobalConfiguration;
import com.huawei.devcloud.monitor.model.JenkinsBean;
import com.huawei.devcloud.monitor.model.JenkinsInfo;
import com.huawei.devcloud.monitor.model.SystemInfo;

import jenkins.model.Jenkins;
import net.bull.javamelody.internal.common.Parameters;
import net.bull.javamelody.internal.model.JavaInformations;

public abstract class AbstractJenkinsInfoGenerator implements JenkinsInfoGenerator {
    protected static final Logger LOGGER = Logger.getLogger(AbstractJenkinsInfoGenerator.class.getName());
    protected Jenkins jenkins;
    protected SystemInfo systemInfo;
    protected JavaInformations javaInformations;
    protected JenkinsInfo jenkinsInfo;

    @Override
    public JenkinsBean generateJenkinsInfo(DevCloudGlobalConfiguration devcloudConfig) {
        JenkinsBean jenkinsBean = getJenkinsBean();
        String monitorUrl = devcloudConfig.getUrl();
        if (StringUtils.isNotBlank(monitorUrl)) {
            try {
                 generateLoadInfo(jenkinsBean);
            } catch (Exception e) {
                LOGGER.warning("-----  [Jenkins Master Monitor] something wrong when post info:" + e);
                for (StackTraceElement element : e.getStackTrace())
                    LOGGER.warning(element.toString());
            }
        }
        return jenkinsBean;
    }

    public abstract void generateLoadInfo(JenkinsBean jenkinsBean);

    public AbstractJenkinsInfoGenerator() {
        jenkins = Jenkins.getInstance();
        javaInformations = new JavaInformations(Parameters.getServletContext(),
                false);
    }

    protected JenkinsBean getJenkinsBean() {
        JenkinsBean jenkinsBean = new JenkinsBean();
        jenkinsBean.setVersion(Jenkins.getVersion().toString());
        String jenkinsUrl = jenkins.getRootUrl();
        jenkinsBean.setUrl(jenkinsUrl);
        jenkinsBean.setLabels(jenkinsInfo.getSlaveLabelMap());
        return jenkinsBean;
    }

}
