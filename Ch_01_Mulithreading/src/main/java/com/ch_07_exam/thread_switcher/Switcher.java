package com.ch_07_exam.thread_switcher;

public class Switcher {

    public static void main(String[] args) throws InterruptedException {

        LeadSubBarrier barrier = new LeadSubBarrier();

        Thread first = new Thread(
                () -> {
                    barrier.tryLead();
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Thread A");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    barrier.doneLead();
                }
        );
        Thread second = new Thread(
                () -> {
                    barrier.trySub();
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Thread B");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    barrier.doneSub();
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
