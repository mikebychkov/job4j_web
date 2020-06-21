package com.ch_02_shared_resources.ex_04_immutable;

public class NodeImmutable<T> {
    private final T value;
    private final NodeImmutable next;

    public NodeImmutable(T value, NodeImmutable next) {
        this.value = value;
        this.next = next;
    }

    public NodeImmutable getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
