package com.huawei.devcloud.global_configuration;

import com.cloudbees.plugins.credentials.Credentials;
import com.cloudbees.plugins.credentials.CredentialsMatcher;
import com.cloudbees.plugins.credentials.common.AbstractIdCredentialsListBoxModel;
import com.cloudbees.plugins.credentials.common.StandardCredentials;
import com.cloudbees.plugins.credentials.common.StandardListBoxModel;
import com.huawei.devcloud.credentials.HWCloudCredentials;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.Util;
import hudson.model.Item;
import hudson.security.ACL;
import hudson.util.ListBoxModel;
import jenkins.model.GlobalConfiguration;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundSetter;

import javax.annotation.CheckForNull;

@Extension
public class DevCloudGlobalConfiguration extends AbstractDevCloudGlobalConfiguration {
    //监控频率
    private Integer interval = DEFAULT_MONITORINTERVAL;
    //耗时比较久的job的时间阈值
    private Integer threshold = DEFAULT_TIMEFORLONG;
    private boolean enable;
    private String dataType;
    private String url;

    @CheckForNull
    private String credentialsId;

    public DevCloudGlobalConfiguration() {
        // When Jenkins is restarted, load any saved configuration from disk.
        load();
    }

    /**
     * @return the singleton instance
     */
    public static DevCloudGlobalConfiguration get() {
        return GlobalConfiguration.all().get(DevCloudGlobalConfiguration.class);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        save();
    }

    public Integer getInterval() {
        return interval;
    }

    @DataBoundSetter
    public void setInterval(Integer interval) {
        this.interval = interval;
        save();
    }

    public Integer getThreshold() {
        return threshold;
    }

    @DataBoundSetter
    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
        save();
    }


    public String getCredentialsId() {
        return credentialsId;
    }

    @DataBoundSetter
    public void setCredentialsId(String credentialsId) {
        this.credentialsId = Util.fixEmpty(credentialsId);
        save();
    }
    
    public boolean isEnable() {
		return enable;
	}
    
    @DataBoundSetter
	public void setEnable(boolean enable) {
		this.enable = enable;
	    save();
	}

	public String getDataType() {
		return dataType;
	}
	
	@DataBoundSetter
	public void setDataType(String dataType) {
		this.dataType = dataType;
	    save();
	}
	
	public ListBoxModel doFillDataTypeItems() {
	    ListBoxModel items = new ListBoxModel();
//	    for (String goal : getDataTypes()) {
	        items.add("internal","internal");
	        items.add("gateway","gateway");
//	    }
	    return items;
	}

	public ListBoxModel doFillCredentialsIdItems() {
        if (Jenkins.getInstance().hasPermission(Item.CONFIGURE)) {
            AbstractIdCredentialsListBoxModel<StandardListBoxModel, StandardCredentials> options = new StandardListBoxModel()
                    .includeEmptyValue()
                    .includeMatchingAs(ACL.SYSTEM,
                            Jenkins.getActiveInstance(),
                            StandardCredentials.class,
                            null,
                            new HWCloudCredentialsMatcher());
            return options;
        }
        return new StandardListBoxModel();
    }


    private static class HWCloudCredentialsMatcher implements CredentialsMatcher {
        @Override
        public boolean matches(@NonNull Credentials credentials) {
            try {
                return credentials instanceof HWCloudCredentials;
            } catch (Throwable e) {
                return false;
            }
        }
    }
}
