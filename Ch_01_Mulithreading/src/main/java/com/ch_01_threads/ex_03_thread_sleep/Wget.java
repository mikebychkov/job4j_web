package com.ch_01_threads.ex_03_thread_sleep;

public class Wget {

    public static void main(String[] args) {
        Thread load = new Thread(
                () -> {
                    for (int i = 0; i <= 100; i++) {
                        System.out.print("\rLoading: " + i + "%");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        load.start();
    }
}
