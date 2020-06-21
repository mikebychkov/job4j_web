package com.ch_02_shared_resources.ex_05_without_shared_resources;

public class ShareNotSafe {

    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);
        Thread first = new Thread(
                () -> {
                    user.setName("rename");
                }
        );
        first.start();
        first.join();
        System.out.println(cache.findById(1).getName());
    }
}
