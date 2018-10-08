package com.huawei.devcloud.monitor;

import com.huawei.devcloud.credentials.HWCloudAccesskeyCredentials;
import com.huawei.devcloud.monitor.model.JenkinsBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

public class AKSKMessageSender extends AbstractMessageSender {
    public AKSKMessageSender(HWCloudAccesskeyCredentials credentials) {
        super(credentials);
    }

    @Override
    public void send(String url, JenkinsBean bean) {
        HWCloudAccesskeyCredentials c = (HWCloudAccesskeyCredentials) (this.credentials);
        String ak = c.getAccessKey().getPlainText();
        String sk = c.getSecretKey().getPlainText();
        try {
            request.postByAksk(url, bean, "application/json", null, null, ak, sk);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
