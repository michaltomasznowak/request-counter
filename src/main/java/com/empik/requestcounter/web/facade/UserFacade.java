package com.empik.requestcounter.web.facade;

import com.empik.requestcounter.web.model.UserDto;

public interface UserFacade {
    void addRequestToUser(String login);
    UserDto getUser(String login);
}
