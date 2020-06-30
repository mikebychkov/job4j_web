package com.ch_05_non_blocking_algorithm.ex_02_non_blocking_cache;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private ConcurrentHashMap<Integer, Base> data = new ConcurrentHashMap<>();

    public void add(Base model) {
        data.putIfAbsent(model.getId(), Base.of(model));
    }

    public void update(Base model) {
        data.computeIfPresent(model.getId(),
                (k, v) -> {
                    if (model.getVersion() <= v.getVersion()) {
                        throw new OptimisticException("Version control violation.");
                    }
                    return model;
                }
        );
    }

    public void delete(Base model) {
        data.remove(model.getId());
    }

    public Base get(int id) {
        return Base.of(data.get(id));
    }
}
