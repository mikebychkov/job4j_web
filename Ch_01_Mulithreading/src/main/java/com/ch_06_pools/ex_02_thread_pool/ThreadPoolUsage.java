package com.ch_06_pools.ex_02_thread_pool;

public class ThreadPoolUsage {

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        pool.work(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Hello world!");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {

                        }
                    }
                }
        );
        pool.work(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Buenas dias!");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {

                        }
                    }
                }
        );
        pool.work(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Gutten tag!");
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {

                        }
                    }
                }
        );
        pool.shutdown();
    }
}
