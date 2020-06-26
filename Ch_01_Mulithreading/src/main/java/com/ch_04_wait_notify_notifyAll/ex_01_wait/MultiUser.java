package com.ch_04_wait_notify_notifyAll.ex_01_wait;

public class MultiUser {

    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread main = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on();
                },
                "Main"
        );
        Thread sub = new Thread(
                () -> {
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Sub"
        );
        main.start();
        sub.start();
    }
}
