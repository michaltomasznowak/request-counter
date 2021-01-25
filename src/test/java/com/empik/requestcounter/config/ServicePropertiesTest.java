package com.empik.requestcounter.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * michal.nowak created on 25/01/2021
 */
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = ServiceProperties.class)
@TestPropertySource("classpath:application.properties")
class ServicePropertiesTest {
    private static final String URL = "https://api.github.com/users/";

    @Autowired
    private ServiceProperties serviceProperties;

    @Test
    void testBindingPropertiesFile() {
        Assertions.assertEquals(URL, serviceProperties.getUsersUrl(), "Incorrect url");
    }
}
