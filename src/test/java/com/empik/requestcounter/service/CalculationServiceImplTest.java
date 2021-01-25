package com.empik.requestcounter.service;

import com.empik.requestcounter.exception.CalculationException;
import com.empik.requestcounter.slo.UserSlo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculationServiceImplTest {
    private static final long FOLLOWERS = 10;
    private static final long PUBLIC_RESPONSE = 89;
    private static final double EXPECTED_RESULT_1 = 1.2;
    private static final double EXPECTED_RESULT_2 = 54.6;

    @InjectMocks
    private CalculationServiceImpl calculationServiceImpl;

    @Test
    void testCalculateCalculationException() {
        UserSlo userSlo = UserSlo.builder().build();
        Assertions.assertThrows(CalculationException.class, () -> {
            calculationServiceImpl.calculate(userSlo);
        });
    }

    @Test
    void testCalculateZeroPublicResponse() {
        UserSlo userSlo = UserSlo.builder().build();
        userSlo.setFollowers(FOLLOWERS);
        double result = calculationServiceImpl.calculate(userSlo);
        Assertions.assertEquals(Double.valueOf(EXPECTED_RESULT_1), result, "Expected result value: " + EXPECTED_RESULT_1);
    }

    @Test
    void testCalculate() {
        UserSlo userSlo = UserSlo.builder().build();
        userSlo.setFollowers(FOLLOWERS);
        userSlo.setPublicRepos(PUBLIC_RESPONSE);
        double result = calculationServiceImpl.calculate(userSlo);
        Assertions.assertEquals(Double.valueOf(EXPECTED_RESULT_2), result, "Expected result value: " + EXPECTED_RESULT_2);
    }
}