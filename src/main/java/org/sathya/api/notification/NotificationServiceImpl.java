package org.sathya.api.notification;

import org.sathya.api.user.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notifyUser(User user) {
        System.out.println("Notification .notify user " + Thread.currentThread().getName());
        System.out.println("User is created for the user : " + user.getUserName());
    }
}
