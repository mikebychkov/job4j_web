package com.ch_04_wait_notify_notifyAll.ex_02_bounded_blocking_queue;

public class SBQUsage {

    public static class Producer implements Runnable {

        private SimpleBlockingQueue<Integer> sbq;
        public Thread t;

        public Producer(SimpleBlockingQueue<Integer> sbq) {
            this.sbq = sbq;
            this.t = new Thread(this, "Producer");
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                sbq.offer(i);
            }
        }
    }

    public static class Consumer implements Runnable {

        private SimpleBlockingQueue<Integer> sbq;
        public Thread t;

        public Consumer(SimpleBlockingQueue<Integer> sbq) {
            this.sbq = sbq;
            this.t = new Thread(this, "Consumer");
        }

        @Override
        public void run() {
            Integer pollRsl;
            while ((pollRsl = sbq.poll()) != null) {
                System.out.printf("Poll %s%n", pollRsl);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(2);

        Producer prod = new Producer(sbq);
        Consumer cons = new Consumer(sbq);

        prod.t.start();
        cons.t.start();

        prod.t.join();
        cons.t.interrupt();
        cons.t.join();

        System.out.println(sbq.toString());
    }
}
