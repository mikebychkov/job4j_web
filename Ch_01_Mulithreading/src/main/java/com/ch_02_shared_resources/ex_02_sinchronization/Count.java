package com.ch_02_shared_resources.ex_02_sinchronization;

public class Count {
    private int value;

    public synchronized void increment() {
        value++;
    }

    public synchronized int get() {
        return value;
    }
}
