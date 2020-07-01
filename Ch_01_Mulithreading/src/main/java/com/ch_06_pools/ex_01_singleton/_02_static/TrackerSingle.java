package com.ch_06_pools.ex_01_singleton._02_static;

import com.ch_06_pools.ex_01_singleton.Item;

public class TrackerSingle {

    private static final TrackerSingle INSTANCE = new TrackerSingle();

    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
