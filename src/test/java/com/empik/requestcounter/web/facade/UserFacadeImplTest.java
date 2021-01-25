package com.empik.requestcounter.web.facade;

import com.empik.requestcounter.dao.UserDao;
import com.empik.requestcounter.domain.User;
import com.empik.requestcounter.mapper.UserMapper;
import com.empik.requestcounter.slo.UserSlo;
import com.empik.requestcounter.web.client.UserClient;
import com.empik.requestcounter.web.model.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserFacadeImplTest {
    private static final String USER_LOGIN = "testUser";

    @Mock
    private UserDao userDao;
    @Mock
    private UserClient userClient;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserDto userDto;
    @Mock
    private UserSlo userSlo;
    @Mock
    private User user;
    @InjectMocks
    private UserFacadeImpl userFacade;

    @Test
    void addRequestToUser() {
        Mockito.when(userMapper.mapDtoToUser(USER_LOGIN)).thenReturn(user);
        userFacade.addRequestToUser(USER_LOGIN);
        Mockito.verify(userMapper, Mockito.times(1)).mapDtoToUser(USER_LOGIN);
        Mockito.verify(userDao, Mockito.times(1)).saveUser(user);
    }

    @Test
    void getUser() {
        Mockito.when(userClient.getUserInfo(USER_LOGIN)).thenReturn(userSlo);
        Mockito.when(userMapper.mapToUserDto(userSlo)).thenReturn(userDto);
        UserDto user = userFacade.getUser(USER_LOGIN);

        Assertions.assertEquals(userDto, user, "Incorrect return User");
        Mockito.verify(userClient, Mockito.times(1)).getUserInfo(USER_LOGIN);
        Mockito.verify(userMapper, Mockito.times(1)).mapToUserDto(userSlo);
    }
}