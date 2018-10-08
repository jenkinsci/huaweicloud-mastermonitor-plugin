package com.huawei.devcloud.global_configuration;

import jenkins.model.GlobalConfiguration;
import jenkins.model.GlobalConfigurationCategory;
import org.apache.commons.lang.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDevCloudGlobalConfiguration  extends GlobalConfiguration {

    private static final Logger LOGGER = Logger.getLogger(AbstractDevCloudGlobalConfiguration.class.getName());
    public static final int DEFAULT_TIMEINTERVAL = 5;

    public static final int DEFAULT_TIMEOUT = 120000;

    public static final int DEFAULT_MONITORINTERVAL = 60;

    public static final int DEFAULT_TIMEFORLONG = 6;

    public static final int B_TO_MB = 1024 * 1024;

    public static final int B_TO_GB = 1024 * 1024 * 1024;

    private Integer timeInterval = DEFAULT_TIMEINTERVAL;

    protected AbstractDevCloudGlobalConfiguration() {}

//    @Override
//    public GlobalConfigurationCategory getCategory() {
//        return GlobalConfigurationCategory.get(DevCloudGlobalConfigurationCategory.class);
//    }

    /**
     * it returns a different cause message based on exception type.
     *
     * @param t
     *            Throwable to process.
     * @return the proper cause message.
     */
    protected String processExceptionMessage(Throwable t) {
        LOGGER.log(Level.FINEST, t.getMessage(), t);

        String msg = t.getMessage();
        String className = t.getClass().getSimpleName();
        return className + ":" + StringUtils.defaultIfBlank(msg, "Unknown error");
    }

}
