package org.dariabodiakova.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class AppConfig {

    private static final Config config = ConfigFactory.load("configuration/application.conf");

    public static String getBaseUrl() {
        return config.getString("app.baseUrl");
    }

    public static String getPageUrl(String pageName) {
        return config.getString("app.pages." + pageName);
    }

    public static String getBaseApiUrl() {
        return config.getString("app.apiUrl");
    }

    public static String getApiEndpoint(String endpoint) {
        return config.getString("app.api." + endpoint);
    }
}
