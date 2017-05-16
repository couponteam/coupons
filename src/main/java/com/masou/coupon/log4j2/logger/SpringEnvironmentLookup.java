package com.masou.coupon.log4j2.logger;

import com.masou.coupon.log4j2.context.SpringContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.AbstractLookup;
import org.apache.logging.log4j.core.lookup.StrLookup;

/**
 * Created by paul on 16/12/21.
 */

@Plugin(name = "spring", category = StrLookup.CATEGORY)
public class SpringEnvironmentLookup extends AbstractLookup {

    @Override
    public String lookup(LogEvent logEvent, String s) {
//        aware.getApplicationContext().getEnvironment();
        return SpringContext.getEnvironment().getProperty(s);
    }
}
