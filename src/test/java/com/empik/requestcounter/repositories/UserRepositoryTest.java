package com.empik.requestcounter.repositories;

import com.empik.requestcounter.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {
    public static String EXISTING_USER_LOGIN = "login1";
    public static String NOT_EXISTING_USER_LOGIN = "login2";
    public static final long REQUEST_COUNT = 100;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .requestCount(REQUEST_COUNT)
                .login(EXISTING_USER_LOGIN).build();
        userRepository.save(user);
    }

    @Test
    void findByLoginExistingUser() {
        Optional<User> userByLogin = userRepository.findByLogin(EXISTING_USER_LOGIN);
        Assertions.assertTrue(userByLogin.isPresent(), "User with login " + EXISTING_USER_LOGIN + "should exist");
        Assertions.assertEquals(userByLogin.get().getLogin(), EXISTING_USER_LOGIN, "Incorrect User Login");
        Assertions.assertEquals(userByLogin.get().getRequestCount(), REQUEST_COUNT, "Incorrect value request count");
        Assertions.assertNotNull(userByLogin.get().getId(), "ID cannot be null");
    }

    @Test
    void findByLoginNotExistingUser() {
        Optional<User> userByLogin = userRepository.findByLogin(NOT_EXISTING_USER_LOGIN);
        Assertions.assertFalse(userByLogin.isPresent(), "User with login " + EXISTING_USER_LOGIN + "should exist");
    }
}