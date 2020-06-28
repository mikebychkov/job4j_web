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
    private final Object monitor = new Object();

    public SimpleBlockingQueue(int bound) {
        this.bound = bound;
    }

    public int size() {
        synchronized (monitor) {
            return queue.size();
        }
    }

    public String toString() {
        synchronized (monitor) {
            return queue.toString();
        }
    }

    public void offer(T value) {
        synchronized (monitor) {
            while (size() == bound) {
                try {
                    System.out.println(Thread.currentThread().getName() + " #wait");
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            queue.offer(value);
            System.out.println(Thread.currentThread().getName() + " #offer " + value);
            notifyStatus();
        }
    }

    public T poll() {
        synchronized (monitor) {
            while (size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " #wait");
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            T rsl = queue.poll();
            System.out.println(Thread.currentThread().getName() + " #poll " + rsl);
            notifyStatus();
            return rsl;
        }
    }

    private void notifyStatus() {
        System.out.println(Thread.currentThread().getName() + " #notifyAll");
        monitor.notifyAll();
    }
}
