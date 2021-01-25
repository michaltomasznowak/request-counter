package com.empik.requestcounter.dao;

import com.empik.requestcounter.domain.User;
import com.empik.requestcounter.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserDaoImplTest {
    private static final String USER_LOGIN = "testLogin";
    private static final long ZERO_REQUEST_COUNT = 0;
    private static final long HUNDRED_REQUEST_COUNT = 100;
    private static final long ID = 22;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDaoImpl userDao;

    @Test
    public void testSaveNotExistingUser(){
        User user = User.builder()
                .login(USER_LOGIN)
                .requestCount(ZERO_REQUEST_COUNT).build();

        Mockito.when(userRepository.findByLogin(USER_LOGIN)).thenReturn(Optional.empty());

        userDao.saveUser(user);
        user.setRequestCount((long) UserDaoImpl.INCREMENT_VALUE);
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void testSaveExistingUser(){
        User user = User.builder()
                .login(USER_LOGIN)
                .requestCount(ZERO_REQUEST_COUNT).build();

        User existingUser = User.builder()
                .id(ID)
                .login(USER_LOGIN)
                .requestCount(HUNDRED_REQUEST_COUNT).build();

        Mockito.when(userRepository.findByLogin(USER_LOGIN)).thenReturn(Optional.of(existingUser));

        userDao.saveUser(user);
        user.setRequestCount(HUNDRED_REQUEST_COUNT + UserDaoImpl.INCREMENT_VALUE);
        Mockito.verify(userRepository).save(existingUser);
    }
}