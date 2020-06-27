package com.ch_04_wait_notify_notifyAll.ex_02_bounded_blocking_queue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        List<Integer> rsl = new ArrayList<>();
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(2);
        Thread producer = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            sbq.offer(i);
                        }
                    }
                },
                "Producer"
        );
        Thread consumer = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (!Thread.currentThread().isInterrupted() || sbq.size() > 0) {
                            rsl.add(sbq.poll());
                        }
                    }
                },
                "Consumer"
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(rsl, is(List.of(0, 1, 2, 3, 4)));
    }
}
