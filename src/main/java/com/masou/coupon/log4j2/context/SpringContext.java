package com.masou.coupon.log4j2.context;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


public class SpringContext {

    private static ConfigurableEnvironment environment;
    private static ApplicationContext applicationContext;

    public static ConfigurableEnvironment getEnvironment() {
        return environment;
    }

    public static void setEnvironment(ConfigurableEnvironment environment) {
        SpringContext.environment = environment;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.applicationContext = applicationContext;
    }
}
