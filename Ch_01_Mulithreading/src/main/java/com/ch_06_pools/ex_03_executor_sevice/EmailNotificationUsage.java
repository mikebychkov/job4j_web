package com.ch_06_pools.ex_03_executor_sevice;

public class EmailNotificationUsage {

    public static void main(String[] args) {
        EmailNotification en = new EmailNotification();
        User user = new User("Valeriy Petrov", "valeriy.petrov@job.com");
        en.emailTo(user);
        en.close();
    }
}
