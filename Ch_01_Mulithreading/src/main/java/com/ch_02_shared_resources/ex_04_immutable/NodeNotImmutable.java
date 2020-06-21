package com.ch_02_shared_resources.ex_04_immutable;

public class NodeNotImmutable<T> {
    private NodeNotImmutable next;
    private T value;

    public NodeNotImmutable getNext() {
        return next;
    }

    public void setNext(NodeNotImmutable next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
