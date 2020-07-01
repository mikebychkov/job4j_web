package com.ch_06_pools.ex_01_singleton._01_enum;

import com.ch_06_pools.ex_01_singleton.Item;

public enum TrackerSingle {
    INSTANCE;

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.INSTANCE;
    }
}
