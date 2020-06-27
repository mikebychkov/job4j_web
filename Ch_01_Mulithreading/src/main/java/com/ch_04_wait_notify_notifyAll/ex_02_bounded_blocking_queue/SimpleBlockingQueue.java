package com.ch_04_wait_notify_notifyAll.ex_02_bounded_blocking_queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private volatile Queue<T> queue = new LinkedList<>();
    private final int bound;

    public SimpleBlockingQueue(int bound) {
        this.bound = bound;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized String toString() {
        return queue.toString();
    }

    public synchronized void offer(T value) {
        while (size() == bound) {
            pause();
        }
        queue.offer(value);
        System.out.println(Thread.currentThread().getName() + " #offer " + value);
        notifyStatus();
    }

    public synchronized T poll() {
        while (size() == 0) {
            pause();
        }
        T rsl = queue.poll();
        System.out.println(Thread.currentThread().getName() + " #poll " + rsl);
        notifyStatus();
        return rsl;
    }

    private void pause() {
        try {
            System.out.println(Thread.currentThread().getName() + " #wait");
            this.wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void notifyStatus() {
        System.out.println(Thread.currentThread().getName() + " #notifyAll");
        this.notifyAll();
    }
}
