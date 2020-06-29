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

    public int size() {
        synchronized (this) {
            return queue.size();
        }
    }

    public String toString() {
        synchronized (this) {
            return queue.toString();
        }
    }

    public void offer(T value) {
        synchronized (this) {
            while (size() == bound) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            queue.offer(value);
            this.notifyAll();
        }
    }

    public T poll() {
        synchronized (this) {
            while (size() == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            T rsl = queue.poll();
            this.notifyAll();
            return rsl;
        }
    }
}
