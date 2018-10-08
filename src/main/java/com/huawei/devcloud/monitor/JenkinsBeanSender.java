package com.huawei.devcloud.monitor;

import com.cloudbees.plugins.credentials.CredentialsMatchers;
import com.cloudbees.plugins.credentials.common.StandardCredentials;
import com.cloudbees.plugins.credentials.domains.DomainRequirement;
import com.huawei.devcloud.credentials.HWCloudCredentials;
import com.huawei.devcloud.global_configuration.DevCloudGlobalConfiguration;
import com.huawei.devcloud.monitor.model.JenkinsBean;
import com.huawei.devcloud.monitor.utils.ReqestClientUtil;
import hudson.model.Item;
import hudson.security.ACL;

import java.util.ArrayList;
import java.util.logging.Logger;

import static com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials;

public class JenkinsBeanSender implements BeanSender {
    private static final Logger LOGGER = Logger.getLogger(JenkinsBeanSender.class.getName());
    protected DevCloudGlobalConfiguration devcloudConfig;
    protected String monitorUrl;
    protected HWCloudCredentials credentials;
    protected MessageSender sender;

    public JenkinsBeanSender(DevCloudGlobalConfiguration devcloudConfig) {
        this.devcloudConfig = devcloudConfig;
        this.monitorUrl = devcloudConfig.getUrl();
    }

    private HWCloudCredentials getCredentials(String credentialsId) {
        if (credentialsId == null) {
            LOGGER.info("No credentials id has been specified!");
            throw new IllegalStateException("No credentialsId has been specified!");
        }
        StandardCredentials credentials = CredentialsMatchers.firstOrNull(
                lookupCredentials(StandardCredentials.class, (Item) null, ACL.SYSTEM, new ArrayList<DomainRequirement>()),
                CredentialsMatchers.withId(credentialsId));
        if (credentials != null && credentials instanceof HWCloudCredentials) {
            return (HWCloudCredentials) credentials;
        }
        throw new IllegalStateException("No HWCloudCredentials found for credentialsId: " + credentialsId);
    }

    @Override
    public void send() {
        JenkinsInfoGenerator generator = GeneratorFactory.generateBean(devcloudConfig);
        JenkinsBean bean = generator.generateJenkinsInfo(devcloudConfig);
        credentials = getCredentials(devcloudConfig.getCredentialsId());
        if (credentials != null) {
            sender = GeneratorFactory.generateSender(credentials);
        }
        if (sender != null) {
            sender.send(this.monitorUrl, bean);
        }
    }
}
