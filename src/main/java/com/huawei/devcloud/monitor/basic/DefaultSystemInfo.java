package com.huawei.devcloud.monitor.basic;

import com.huawei.devcloud.monitor.model.*;
import net.bull.javamelody.internal.model.JavaInformations;

public class DefaultSystemInfo extends SystemInfo {

    public DefaultSystemInfo(JavaInformations javaInformations) {
        super(javaInformations);
    }

    @Override
    public HttpCountInfo getHttpRequestCount(int allhttpCount, int httpErrorCount) {
        return  new HttpCount(allhttpCount,httpErrorCount);
    }

    @Override
    public ThreadCountInfo getThreadCount(long allThreadCount, int activeThreadCount) {
        return new ThreadCount(allThreadCount,activeThreadCount);
    }
}
