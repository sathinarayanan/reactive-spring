package org.sathya.api.user;

import org.sathya.api.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public Mono<User> signup(User user) {
        System.out.println("User Service .signup " + Thread.currentThread().getName());
        return userRepository.save(user)
                .doOnSuccess(this.notificationService::notifyUser)
                .doOnError(e -> System.err.println("Error while creating user"));
    }

    @Override
    public Mono<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Flux<UserEvent> getUserEventByUserName(String userName) {
        return getUserByUserName(userName).flatMapMany(user -> {
            Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
            Flux<UserEvent> userEventFlux = Flux.fromStream(Stream.generate(() -> new UserEvent(user.getUserName(), new Date())));
            Flux<Tuple2<Long, UserEvent>> zip = Flux.zip(interval, userEventFlux);
            return zip.map(Tuple2::getT2);
        });
    }
}
