package com.ch_05_non_blocking_algorithm.ex_01_compare_and_swap;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount<T> {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        count.set(0);
    }

    public void increment() {
        Integer i;
        do {
            i = count.get();
        } while (!count.compareAndSet(i, i + 1));
    }

    public int get() {
        return count.get();
    }
}

