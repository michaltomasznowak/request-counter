package com.empik.requestcounter.mapper;

import com.empik.requestcounter.domain.User;
import com.empik.requestcounter.service.CalculationService;
import com.empik.requestcounter.slo.UserSlo;
import com.empik.requestcounter.web.model.UserDto;
import org.springframework.stereotype.Component;

/**
 * michal.nowak created on 24/01/2021
 */
@Component
public class UserMapperImpl implements UserMapper {

    private final CalculationService calculationService;

    public UserMapperImpl(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @Override
    public User mapDtoToUser(String login) {
        return User.builder()
                .login(login)
                .requestCount(0L)
                .build();
    }

    @Override
    public UserDto mapToUserDto(UserSlo userSlo) {
        return UserDto.builder()
                .id(userSlo.getId())
                .login(userSlo.getLogin())
                .name(userSlo.getName())
                .type(userSlo.getType())
                .avatarUrl(userSlo.getAvatarUrl())
                .createdAt(userSlo.getCreateDate())
                .calculations(calculationService.calculate(userSlo))
                .build();
    }
}
