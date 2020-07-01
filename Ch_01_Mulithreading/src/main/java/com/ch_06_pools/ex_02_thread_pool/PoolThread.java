package com.ch_06_pools.ex_02_thread_pool;

import com.ch_04_wait_notify_notifyAll.ex_02_bounded_blocking_queue.SimpleBlockingQueue;

public class PoolThread implements Runnable {

    private final SimpleBlockingQueue<Runnable> tasks;
    public final Thread t;
    private final ThreadPool monitor;

    public PoolThread(SimpleBlockingQueue<Runnable> tasks, int tNum, ThreadPool monitor) {
        this.tasks = tasks;
        this.monitor = monitor;
        this.t = new Thread(this, "Pool thread #" + tNum);
        this.t.start();
    }

    @Override
    public void run() {
        Runnable r = null;
        while (!Thread.currentThread().isInterrupted() || tasks.size() > 0) {
            while ((r = tasks.poll()) == null) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            Thread t1 = new Thread(r);
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
