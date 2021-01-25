package com.empik.requestcounter.service;

import com.empik.requestcounter.slo.UserSlo;

public interface CalculationService {
    double calculate(UserSlo userSlo);
}
