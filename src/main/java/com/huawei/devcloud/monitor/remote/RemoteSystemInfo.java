package com.huawei.devcloud.monitor.remote;

import com.huawei.devcloud.monitor.model.*;
import net.bull.javamelody.internal.model.JavaInformations;

public class RemoteSystemInfo extends SystemInfo {

    public RemoteSystemInfo(JavaInformations javaInformations) {
        super(javaInformations);
    }

    @Override
    public HttpCountInfo getHttpRequestCount(int allHttpCount, int httpErrorCount) {
        return  new HttpCountB(allHttpCount,httpErrorCount);
    }

    @Override
    public ThreadCountInfo getThreadCount(long allThreadCount, int activeThreadCount) {
        return new ThreadCountB(allThreadCount,activeThreadCount);
    }

}
