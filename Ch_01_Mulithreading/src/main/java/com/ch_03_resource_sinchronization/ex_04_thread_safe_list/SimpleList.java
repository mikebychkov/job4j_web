package com.ch_03_resource_sinchronization.ex_04_thread_safe_list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleList<T> implements Iterable<T> {

    private int size = 0;
    private int modCount = 0;

    private Node first;
    private Node last;

    public SimpleList() {
    }

    public void add(T model) {
        Node n = new Node(model);
        connectNode(n);
        this.last = n;
        if (this.first == null) {
            this.first = n;
        }
        this.size++;
        this.modCount++;
    }

    public T get(int index) {
        Node rsl = getNode(index);
        return rsl.value;
    }

    private Node getNode(int index) {
        Node rsl = null;
        if (index < getHalfSize()) {
            rsl = getNodeAsc(index);
        } else {
            rsl = getNodeDesc(index);
        }
        if (rsl == null) {
            throw new NoSuchElementException();
        }
        return rsl;
    }

    private Node getNodeAsc(int index) {
        if (this.first == null) {
            return null;
        }
        Node n = this.first;
        for (int i = 0; i <= getHalfSize(); i++) {
            if (i == index) {
                return n;
            }
            n = n.next;
        }
        return null;
    }

    private Node getNodeDesc(int index) {
        if (this.last == null) {
            return null;
        }
        Node n = this.last;
        for (int i = this.size - 1; i > getHalfSize(); i--) {
            if (i == index) {
                return n;
            }
            n = n.prev;
        }
        return null;
    }

    private int getHalfSize() {
        return this.size / 2 + 1;
    }

    public int size() {
        return this.size;
    }

    public void remove(int index) {
        Node rsl = getNode(index);
        if (rsl.prev != null) {
            rsl.prev.next = rsl.next;
        }
        if (rsl.next != null) {
            rsl.next.prev = rsl.prev;
        }
        this.size--;
        if (this.size == 0) {
            this.first = null;
            this.last = null;
        }
    }

    private class Node {
        T value;
        Node prev;
        Node next;
        public Node(T value) {
            this.value = value;
        }
    }

    private void connectNode(Node n) {
        n.prev = this.last;
        if (this.last != null) {
            this.last.next = n;
        }
    }

    public SimpleList<T> clone() {
        SimpleList<T> rsl = new SimpleList<>();
        this.forEach(rsl::add);
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleIterator(this);
    }

    private class SimpleIterator implements Iterator<T> {

        private SimpleList<T> sl;
        private Node resent;
        private int modCount = 0;

        public SimpleIterator(SimpleList<T> sl) {
            this.sl = sl;
            this.resent = new Node(null);
            this.resent.next = sl.first;
            this.modCount = sl.modCount;
        }

        @Override
        public boolean hasNext() {
            return this.resent.next != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.modCount != sl.modCount) {
                throw new ConcurrentModificationException();
            }
            this.resent = this.resent.next;
            return this.resent.value;
        }
    }
}
