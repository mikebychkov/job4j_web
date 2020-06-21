package com.ch_01_threads.ex_05_blocked_thread_interrupt;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] markers = {"|", "/", "--", "\\"};
        int markerIndex = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rLoading... " + markers[markerIndex++]);
            if (markerIndex == markers.length) {
                markerIndex = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}
