package com.empik.requestcounter.mapper;
import com.empik.requestcounter.domain.User;
import com.empik.requestcounter.slo.UserSlo;
import com.empik.requestcounter.web.model.UserDto;

/**
 * michal.nowak created on 24/01/2021
 */
public interface UserMapper {
    UserDto mapToUserDto(UserSlo userSlo);
    User mapDtoToUser(String login);
}
