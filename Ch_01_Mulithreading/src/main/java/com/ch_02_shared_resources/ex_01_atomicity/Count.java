package com.ch_02_shared_resources.ex_01_atomicity;

public class Count {
    private int value;

    public void increment() {
        value++;
    }

    public int get() {
        return value;
    }
}
