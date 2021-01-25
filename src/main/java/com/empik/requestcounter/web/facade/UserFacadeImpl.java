package com.empik.requestcounter.web.facade;

import com.empik.requestcounter.dao.UserDao;
import com.empik.requestcounter.domain.User;
import com.empik.requestcounter.mapper.UserMapper;
import com.empik.requestcounter.slo.UserSlo;
import com.empik.requestcounter.web.client.UserClient;
import com.empik.requestcounter.web.model.UserDto;
import org.springframework.stereotype.Component;

/**
 * michal.nowak created on 24/01/2021
 */
@Component
public class UserFacadeImpl implements UserFacade {
    private final UserDao userDao;
    private final UserClient userClient;
    private final UserMapper userMapper;

    public UserFacadeImpl(UserDao userDao, UserClient userClient, UserMapper userMapper) {
        this.userDao = userDao;
        this.userClient = userClient;
        this.userMapper = userMapper;
    }

    @Override
    public void addRequestToUser(String login) {
        User user = userMapper.mapDtoToUser(login);
        userDao.saveUser(user);
    }

    @Override
    public UserDto getUser(String login) {
        UserSlo userSlo = userClient.getUserInfo(login);
        return userMapper.mapToUserDto(userSlo);
    }
}
