package com.ch_06_pools.ex_02_thread_pool;

import java.util.Random;

public class ThreadPoolUsage {

    public static void main(String[] args) {
        Random rnd = new Random();

        ThreadPool pool = new ThreadPool();
        pool.work(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Hello world!");
                        try {
                            Thread.sleep(rnd.nextInt(1000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        pool.work(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Buenas dias!");
                        try {
                            Thread.sleep(rnd.nextInt(1000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        pool.work(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Gutten tag!");
                        try {
                            Thread.sleep(rnd.nextInt(1000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        pool.shutdown();
    }
}
