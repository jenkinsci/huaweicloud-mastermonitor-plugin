package com.huawei.devcloud.monitor.model;

import com.huawei.devcloud.monitor.model.*;
import net.bull.javamelody.internal.model.JavaInformations;
import net.bull.javamelody.internal.model.TomcatInformations;

import java.util.List;

public abstract class SystemInfo {
    protected transient JavaInformations javaInformations;

    public SystemInfo(JavaInformations javaInformations) {
        this.javaInformations = javaInformations;
    }

    public HttpCountInfo getHttpRequestCount() {
        List<TomcatInformations> tomcatInformationsList = javaInformations.getTomcatInformationsList();
        int httpCount = 0;
        int httpErrorCount = 0;
        for (TomcatInformations tomcatInfos : tomcatInformationsList) {
            httpErrorCount += tomcatInfos.getErrorCount();
            httpCount += tomcatInfos.getRequestCount();
        }
        return getHttpRequestCount(httpCount, httpErrorCount);
    }

    public abstract HttpCountInfo getHttpRequestCount(int allHttpCount, int httpErrorCount);

    public ThreadCountInfo getThreadCount() {
        int activeThreadCount = javaInformations.getThreadCount();
        long threadCount = javaInformations.getTotalStartedThreadCount();
        return getThreadCount(threadCount, activeThreadCount);
    }

    public abstract ThreadCountInfo getThreadCount(long allThreadCount, int activeThreadCount);
}

