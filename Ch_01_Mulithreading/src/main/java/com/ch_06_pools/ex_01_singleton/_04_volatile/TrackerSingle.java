package com.ch_06_pools.ex_01_singleton._04_volatile;

import com.ch_06_pools.ex_01_singleton.Item;

public class TrackerSingle {

    private static volatile TrackerSingle instance;

    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        if (instance == null) {
            synchronized (TrackerSingle.class) {
                if (instance == null) {
                    instance = new TrackerSingle();
                }
            }
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
