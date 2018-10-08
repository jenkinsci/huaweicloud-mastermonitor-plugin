package com.huawei.devcloud.monitor;

import com.huawei.devcloud.credentials.HWCloudIAMUserCredentials;
import com.huawei.devcloud.monitor.model.JenkinsBean;
import com.huawei.devcloud.monitor.utils.AuthException;
import com.huawei.devcloud.monitor.utils.IamJudge;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Logger;

public class IAMUserMessageSender extends AbstractMessageSender {
    private static final Logger LOGGER = Logger.getLogger(IAMUserMessageSender.class.getName());
    public IAMUserMessageSender(HWCloudIAMUserCredentials credentials) {
        super(credentials);
    }

    @Override
    public void send(String url, JenkinsBean bean) {
        String token = getToken();
        try {
            request.post(url, bean, "application/json", null, null, token);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }

    private String getToken() {
        String token = null;
        HWCloudIAMUserCredentials c = (HWCloudIAMUserCredentials) credentials;
        String iamUrl = c.getIamUrl();
        String userName = c.getUserName().getPlainText();
        String passWord = c.getPassword().getPlainText();
        IamJudge iamJudge = new IamJudge(iamUrl, userName, passWord);
        try {
            token = iamJudge.getCommonToken();
//                LOGGER.info("request token is: " + token);
        } catch (AuthException e) {
            LOGGER.info("[ERROR]: getToken error: " + e);
        }
        return token;
    }
}
