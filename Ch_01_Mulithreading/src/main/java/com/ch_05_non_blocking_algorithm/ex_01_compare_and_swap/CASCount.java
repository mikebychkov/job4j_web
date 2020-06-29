package com.ch_05_non_blocking_algorithm.ex_01_compare_and_swap;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount<T> {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        throw new UnsupportedOperationException("Count is not impl.");
    }

    public int get() {
        throw new UnsupportedOperationException("Count is not impl.");
    }
}

