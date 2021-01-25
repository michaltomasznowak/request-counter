package com.empik.requestcounter.service;

import com.empik.requestcounter.exception.CalculationException;
import com.empik.requestcounter.slo.UserSlo;
import org.springframework.stereotype.Service;

/**
 * michal.nowak created on 21/01/2021
 */
@Service
public class CalculationServiceImpl implements CalculationService {
    private static final double DIVIDEND_VALUE = 6;
    private static final double ADD_VALUE = 2;

    public double calculate(UserSlo userSlo) {
        if (userSlo.getFollowers() == 0) {
            throw new CalculationException("Divider cannot be null");
        }
        return DIVIDEND_VALUE / userSlo.getFollowers() * (ADD_VALUE + userSlo.getPublicRepos());
    }

}
