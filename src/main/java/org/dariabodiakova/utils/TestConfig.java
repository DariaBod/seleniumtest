package org.dariabodiakova.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TestConfig {

    private static final Config config = ConfigFactory.load("configuration/test.conf");

    public static String[] getBrowsers() {
        return config.getStringList("test.browsers").toArray(new String[0]);
    }

    public static boolean isHeadless() {
        return config.getBoolean("test.headless");
    }

    public static int getTimeoutSeconds() {
        return config.getInt("test.timeoutSeconds");
    }

    public static int getThreadCount() {
        return config.getInt("test.threadCount");
    }
}
