package com.empik.requestcounter.mapper;

import com.empik.requestcounter.domain.User;
import com.empik.requestcounter.service.CalculationService;
import com.empik.requestcounter.slo.UserSlo;
import com.empik.requestcounter.web.model.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.OffsetDateTime;

@ExtendWith(MockitoExtension.class)
class UserMapperImplTest {
    private static final long EXPECTED_ID = 583231;
    private static final long EXPECTED_FOLLOWERS = 3474;
    private static final long EXPECTED_PUBLIC_REPOS = 8;
    private static final double EXPECTED_CALCULATION_RESULT = 10.34;
    private static final String EXPECTED_AVATAR_URL = "https://avatars.githubusercontent.com/u/583231?v=4";
    private static final String EXPECTED_CREATED_DATE = "2011-01-25T18:44:36Z";
    private static final String EXPECTED_LOGIN = "octocat";
    private static final String EXPECTED_NAME = "The Octocat";
    private static final String EXPECTED_TYPE = "User";

    @Mock
    private CalculationService calculationService;

    @InjectMocks
    private UserMapperImpl userMapper;

    @Test
    void mapDtoToUser() {
        User user = userMapper.mapDtoToUser(EXPECTED_LOGIN);

        Assertions.assertEquals(EXPECTED_LOGIN, user.getLogin(), "Incorrect User Login");
        Assertions.assertNull(user.getId(), "Incorrect User Id");
        Assertions.assertEquals(0, user.getRequestCount(), "Incorrect User RequestCount");
    }

    @Test
    void mapToUserDto() {
        UserSlo userSlo = UserSlo.builder()
                .id(EXPECTED_ID)
                .avatarUrl(EXPECTED_AVATAR_URL)
                .createDate(OffsetDateTime.parse(EXPECTED_CREATED_DATE))
                .followers(EXPECTED_FOLLOWERS)
                .login(EXPECTED_LOGIN)
                .name(EXPECTED_NAME)
                .publicRepos(EXPECTED_PUBLIC_REPOS)
                .type(EXPECTED_TYPE).build();

        Mockito.when(calculationService.calculate(userSlo)).thenReturn(EXPECTED_CALCULATION_RESULT);
        UserDto userDto = userMapper.mapToUserDto(userSlo);
        Mockito.verify(calculationService, Mockito.times(1)).calculate(userSlo);

        Assertions.assertEquals(EXPECTED_ID, userDto.getId(), "Incorrect UserDto ID");
        Assertions.assertEquals(EXPECTED_AVATAR_URL, userDto.getAvatarUrl(), "Incorrect UserDto Avatar url");
        Assertions.assertEquals(EXPECTED_CREATED_DATE, userDto.getCreatedAt().toString(), "Incorrect UserDto Created Date");
        Assertions.assertEquals(EXPECTED_LOGIN, userDto.getLogin(), "Incorrect UserDto Login");
        Assertions.assertEquals(EXPECTED_NAME, userDto.getName(), "Incorrect UserDto Name");
        Assertions.assertEquals(EXPECTED_TYPE, userDto.getType(), "Incorrect UserDto Type");
    }
}