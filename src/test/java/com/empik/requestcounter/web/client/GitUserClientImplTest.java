package com.empik.requestcounter.web.client;

import com.empik.requestcounter.config.ServiceProperties;
import com.empik.requestcounter.slo.UserSlo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class GitUserClientImplTest {
    private static final String LOGIN = "userLogin";
    private static final String URL = "https://api.github.com/users/";
    private static final String RESPONSE_JSON = "{\"name\":\"john\",\"age\":22,\"class\":\"mca\"}";

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private RestTemplateBuilder restTemplateBuilder;
    @Mock
    private ServiceProperties serviceProperties;
    @Mock
    private UserSlo expectedUserSlo;

    @InjectMocks
    private GitUserClientImpl gitUserClient;

    @BeforeEach
    void setUp() {
        Mockito.when(serviceProperties.getUsersUrl()).thenReturn(URL);
        Mockito.when(restTemplateBuilder.build()).thenReturn(restTemplate);
        gitUserClient = new GitUserClientImpl(restTemplateBuilder, serviceProperties);
    }

    @Test
    public void testGetUser() {
        Mockito.when(restTemplate.getForObject(URL + LOGIN, UserSlo.class)).thenReturn(expectedUserSlo);
        UserSlo userSlo = gitUserClient.getUserInfo(LOGIN);
        Assertions.assertEquals(expectedUserSlo, userSlo, "Incorrect returned userSlo");
    }
}