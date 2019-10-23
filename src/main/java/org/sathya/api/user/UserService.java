package org.sathya.api.user;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> signup(User user);

    Mono<User> getUserByUserName(String userName);

    Flux<User> findAll();

    Flux<UserEvent> getUserEventByUserName(String userName);
}
