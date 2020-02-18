package com.se.sample;

import java.util.concurrent.locks.ReentrantLock;

public class Increment implements  Runnable {

    private Counter counter;
    private String name;
    ReentrantLock locker;

    public Increment(String name, Counter counter, ReentrantLock locker) {
        this.counter = counter;
        this.name = name;
        this.locker = locker;
    }

    @Override
    public void run() {
        try {
            while (true) {

                if (ThreadHelper.checkBreakCondition(locker, name, this.counter.continueProducing))
                    break;

                counter.increment(counter.getIncrementValue(), this.name);
                Thread.sleep(1000);
            }

            System.out.println(name + " finished its job; terminating...");

        } catch (InterruptedException ex) {
            ex.printStackTrace();

        } finally {
            // situation when we got exception from counter
            // or exception from  ThreadHelper
            if (locker.isLocked()) {
                locker.unlock();
            }
        }
    }
}


