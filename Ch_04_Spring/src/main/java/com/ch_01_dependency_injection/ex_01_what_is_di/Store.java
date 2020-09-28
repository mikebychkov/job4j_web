package com.ch_01_dependency_injection.ex_01_what_is_di;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<String> data = new ArrayList<String>();

    public void add(String value) {
        data.add(value);
    }

    public List<String> getAll() {
        return data;
    }
}
