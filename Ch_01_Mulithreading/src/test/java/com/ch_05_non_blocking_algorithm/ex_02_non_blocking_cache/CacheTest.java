package com.ch_05_non_blocking_algorithm.ex_02_non_blocking_cache;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CacheTest {

    @Test
    public void test() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();

        Cache cc = new Cache();
        cc.add(new Base(1, "Task #1"));

        Thread t1 = new Thread(
                () -> {
                    Base b = cc.get(1);
                    b.setName("Task numero uno");
                    try {
                        cc.update(b);
                    } catch (RuntimeException e) {
                        ex.set(e);
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    Base b = cc.get(1);
                    b.setName("Task numero supremo");
                    try {
                        cc.update(b);
                    } catch (RuntimeException e) {
                        ex.set(e);
                    }
                }
        );
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertThat(ex.get().getMessage(), is("Version control violation."));
    }
}
