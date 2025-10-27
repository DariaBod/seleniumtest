package org.dariabodiakova.models;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Users {

    private static final Config config = ConfigFactory.load("users.conf");

    public static User admin() {
        return new User(
                config.getString("users.admin.email"),
                config.getString("users.admin.password")
        );
    }

    public static User regular() {
        return new User(
                config.getString("users.regular.email"),
                config.getString("users.regular.password")
        );
    }
}
