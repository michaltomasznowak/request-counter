package com.empik.requestcounter.web.controller;

import com.empik.requestcounter.web.facade.UserFacadeImpl;
import com.empik.requestcounter.web.model.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    private static final String USER_LOGIN = "Login";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacadeImpl userFacade;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
    }

    @Test
    public void testGetUser() throws Exception {
        Mockito.when(userFacade.getUser(USER_LOGIN)).thenReturn(userDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + USER_LOGIN).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}