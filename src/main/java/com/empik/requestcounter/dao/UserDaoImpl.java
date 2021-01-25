package com.empik.requestcounter.dao;

import com.empik.requestcounter.domain.User;
import com.empik.requestcounter.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * michal.nowak created on 24/01/2021
 */
@Service
@Transactional
public class UserDaoImpl implements UserDao {
    public static final int INCREMENT_VALUE = 1;
    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        Optional<User> userByLogin =
                userRepository.findByLogin(user.getLogin());
        return userByLogin.map(this::incrementRequest).orElseGet(() -> incrementRequest(user));
    }

    private User incrementRequest(User user) {
        user.setRequestCount(user.getRequestCount() + INCREMENT_VALUE);
        return userRepository.save(user);
    }
}
