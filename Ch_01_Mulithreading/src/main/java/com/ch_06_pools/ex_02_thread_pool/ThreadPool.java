package com.ch_06_pools.ex_02_thread_pool;

import com.ch_04_wait_notify_notifyAll.ex_02_bounded_blocking_queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;
    private final int size;

    public ThreadPool() {
        size = Runtime.getRuntime().availableProcessors();
        tasks = new SimpleBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            threads.add(new PoolThread(tasks, i, this).t);
        }
    }

    public synchronized void work(Runnable job) {
        tasks.offer(job);
        this.notifyAll();
    }

    public void shutdown() {
        for (Thread t : threads) {
            t.interrupt();
        }
    }
}
