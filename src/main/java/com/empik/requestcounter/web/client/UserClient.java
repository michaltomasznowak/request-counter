package com.empik.requestcounter.web.client;

import com.empik.requestcounter.slo.UserSlo;

/**
 * michal.nowak created on 21/01/2021
 */
public interface UserClient {
    UserSlo getUserInfo(String userName);
}
