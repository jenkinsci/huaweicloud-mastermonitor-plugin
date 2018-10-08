package com.huawei.devcloud.monitor;

import com.huawei.devcloud.credentials.HWCloudAccesskeyCredentials;
import com.huawei.devcloud.credentials.HWCloudCredentials;
import com.huawei.devcloud.monitor.utils.ReqestClientUtil;

public abstract class AbstractMessageSender implements MessageSender{
    protected HWCloudCredentials credentials;
    protected ReqestClientUtil request;

    public AbstractMessageSender(HWCloudCredentials credentials) {
        this.credentials = credentials;
        request = new ReqestClientUtil();
        request.setSocketTimeOut(5);
    }
}
