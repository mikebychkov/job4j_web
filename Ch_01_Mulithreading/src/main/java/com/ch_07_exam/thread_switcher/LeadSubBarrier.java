package com.ch_07_exam.thread_switcher;

public class LeadSubBarrier {

    private boolean leadIsOn;
    private boolean subIsOn;

    public synchronized void tryLead() {
        while (subIsOn) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        leadIsOn = true;
    }

    public synchronized void trySub() {
        while (leadIsOn) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        subIsOn = true;
    }

    public synchronized void doneLead() {
        leadIsOn = false;
        this.notifyAll();
    }

    public synchronized void doneSub() {
        subIsOn = false;
        this.notifyAll();
    }
}
