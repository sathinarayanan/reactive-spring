package org.sathya.controller;

import org.sathya.api.user.User;
import org.sathya.api.user.UserApiConstants;
import org.sathya.api.user.UserEvent;
import org.sathya.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(UserApiConstants.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(UserApiConstants.SIGNUP)
    public Mono<User> signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @GetMapping
    public Flux<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(UserApiConstants.FIND_BY_USER_NAME)
    public Mono<User> getUserByUserName(@PathVariable("userName") String userName) {
        return userService.getUserByUserName(userName);
    }

    @GetMapping(value = UserApiConstants.FIND_USER_EVENT_BY_USER_NAME, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserEvent> getUserEventByUserName(@PathVariable("userName") String userName) {
        System.out.println("UserEvent");
        return userService.getUserEventByUserName(userName);
    }
}
