package com.ch_05_non_blocking_algorithm.ex_01_compare_and_swap;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class CASCountTest {

    @Test
    public void test() throws InterruptedException {
        CASCount cc = new CASCount();
        Thread t1 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        cc.increment();
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        cc.increment();
                    }
                }
        );
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(cc.get(), is(20));
    }
}
