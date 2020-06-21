package com.ch_01_threads.ex_03_thread_sleep;

public class ThreadSleep {

    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ... ");
                        Thread.sleep(3000);
                        System.out.println("Loaded.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        System.out.println("Main");
        while (thread.getState() != Thread.State.TERMINATED) {
            System.out.println(thread.getState());
        }
    }
}
