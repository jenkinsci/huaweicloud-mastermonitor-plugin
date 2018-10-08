package com.huawei.devcloud.monitor;

import com.huawei.devcloud.credentials.HWCloudAccesskeyCredentials;
import com.huawei.devcloud.credentials.HWCloudCredentials;
import com.huawei.devcloud.credentials.HWCloudIAMUserCredentials;
import com.huawei.devcloud.global_configuration.DevCloudGlobalConfiguration;
import com.huawei.devcloud.monitor.basic.DefaultJenkinsInfoGenerator;
import com.huawei.devcloud.monitor.remote.RemoteJenkinsInfoGenerator;

public class GeneratorFactory {

    public static JenkinsInfoGenerator generateBean(DevCloudGlobalConfiguration devcloudConfig){
        if(devcloudConfig==null){
            return null;
        }
        if("gateway".equals(devcloudConfig.getDataType())){
          return new RemoteJenkinsInfoGenerator();
        }
        return new DefaultJenkinsInfoGenerator();
    }

    public static MessageSender generateSender(HWCloudCredentials credentials){
        if(credentials==null){
            return null;
        }
        if(credentials instanceof HWCloudAccesskeyCredentials){
            return new AKSKMessageSender((HWCloudAccesskeyCredentials) credentials);
        }
        if(credentials instanceof HWCloudIAMUserCredentials){
            return new IAMUserMessageSender((HWCloudIAMUserCredentials) credentials);
        }
        return null;
    }

}
