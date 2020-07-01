package com.ch_06_pools.ex_01_singleton._03_synchronized;

import com.ch_06_pools.ex_01_singleton.Item;

public class TrackerSingle {

    private static TrackerSingle instance;

    private TrackerSingle() {
    }

    public static synchronized TrackerSingle getInstance() {
        if (instance == null) {
            instance = new TrackerSingle();
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
