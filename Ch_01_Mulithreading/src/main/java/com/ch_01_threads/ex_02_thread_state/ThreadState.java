package com.ch_01_threads.ex_02_thread_state;

public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                }
        );
        first.start();

        Thread second = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName());
                }
        );
        second.start();

        while (first.getState() != Thread.State.TERMINATED) {
            Thread.sleep(10);
        }
        while (second.getState() != Thread.State.TERMINATED) {
            Thread.sleep(10);
        }
        System.out.println("All jobs are complete!");
    }
}
