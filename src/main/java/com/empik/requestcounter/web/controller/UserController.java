package com.empik.requestcounter.web.controller;

import com.empik.requestcounter.web.facade.UserFacade;
import com.empik.requestcounter.web.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * michal.nowak created on 21/01/2021
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserDto> getUser(@PathVariable("login") String login) {
        userFacade.addRequestToUser(login);
        UserDto user = userFacade.getUser(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
