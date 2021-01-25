package com.empik.requestcounter.web.controller;

import com.empik.requestcounter.exception.CalculationException;
import com.empik.requestcounter.web.facade.UserFacadeImpl;
import com.empik.requestcounter.web.model.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;

import static com.empik.requestcounter.web.controller.RestTemplateResponseErrorHandler.ERROR_CALCULATION;
import static com.empik.requestcounter.web.controller.RestTemplateResponseErrorHandler.ERROR_HTTP_CLIENT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class RestTemplateResponseErrorHandlerTest {
    private static final String USER_LOGIN = "Login";
    private static final String ERROR_HTTP_CLIENT_MESSAGE = "Message Http Client Exception";
    private static final String ERROR_CALCULATE_MESSAGE = "Message Calculate Exception";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserFacadeImpl userFacade;

    @Test
    public void testHttpClientErrorException() throws Exception {
        Mockito.when(userFacade.getUser(USER_LOGIN)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, ERROR_HTTP_CLIENT_MESSAGE));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/" + USER_LOGIN).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiError response = objectMapper.readValue(contentAsString, ApiError.class);

        Assertions.assertEquals(Collections.singletonList(ERROR_HTTP_CLIENT), response.getErrors(), "Incorrect Error message");
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatus(), "Incorrect Status Code");
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value() + " " + ERROR_HTTP_CLIENT_MESSAGE, response.getMessage(), "Incorrect message");
    }

    @Test
    public void testCalculationException() throws Exception {
        Mockito.when(userFacade.getUser(USER_LOGIN)).thenThrow(new CalculationException(ERROR_CALCULATE_MESSAGE));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/users/" + USER_LOGIN).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiError response = objectMapper.readValue(contentAsString, ApiError.class);

        Assertions.assertEquals(Collections.singletonList(ERROR_CALCULATION), response.getErrors(), "Incorrect Error message");
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatus(), "Incorrect Status Code");
        Assertions.assertEquals(ERROR_CALCULATE_MESSAGE, response.getMessage(), "Incorrect message");
    }
}