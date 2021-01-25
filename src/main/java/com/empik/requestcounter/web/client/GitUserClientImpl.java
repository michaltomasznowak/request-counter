package com.empik.requestcounter.web.client;

import com.empik.requestcounter.config.ServiceProperties;
import com.empik.requestcounter.slo.UserSlo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * michal.nowak created on 21/01/2021
 */
@Component
public class GitUserClientImpl implements UserClient {

    private final RestTemplate restTemplate;
    private final ServiceProperties serviceProperties;

    public GitUserClientImpl(RestTemplateBuilder restTemplateBuilder, ServiceProperties serviceProperties){
        this.restTemplate = restTemplateBuilder.build();
        this.serviceProperties = serviceProperties;
    }

    @Override
    public UserSlo getUserInfo(String userName) {
        return restTemplate.getForObject(serviceProperties.getUsersUrl() + userName, UserSlo.class);
    }
}
