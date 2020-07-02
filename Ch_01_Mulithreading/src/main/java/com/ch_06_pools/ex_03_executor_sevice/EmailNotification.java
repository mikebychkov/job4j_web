package com.ch_06_pools.ex_03_executor_sevice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    private String getSSubject(User user) {
        String subject = "Notification {username} to email {email}";
        subject = subject.replace("{username}", user.getName());
        subject = subject.replace("{email}", user.getEmail());
        return subject;
    }

    private String getBody(User user) {
        String body = "Add a new event to {username}";
        body = body.replace("{username}", user.getName());
        return body;
    }

    public void emailTo(User user) {
        pool.submit(
            () -> {
                String subject = getSSubject(user);
                String body = getBody(user);
                send(subject, body, user.getEmail());
            }
        );
    }

    public void send(String subject, String body, String email) {
        System.out.printf("Sending email notification: %s%nSubject: %s%nBody: %s%n", email, subject, body);
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
