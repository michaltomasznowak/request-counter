package com.empik.requestcounter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * michal.nowak created on 25/01/2021
 */
@Configuration
@ConfigurationProperties(prefix = "api.git", ignoreUnknownFields = false)
public class ServiceProperties {
    private String usersUrl;

    public void setUsersUrl(String usersUrl) {
        this.usersUrl = usersUrl;
    }

    public String getUsersUrl() {
        return usersUrl;
    }
}
