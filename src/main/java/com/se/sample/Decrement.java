package com.se.sample;

import java.util.concurrent.locks.ReentrantLock;

public class Decrement implements Runnable {

    private ReentrantLock locker;
    private Counter counter;
    private String name;


    public Decrement(String name, Counter counter, ReentrantLock locker) {
        this.locker = locker;
        this.counter = counter;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (this.counter.continueProducing) {
                if (ThreadHelper.checkBreakCondition(locker, name, this.counter.continueProducing))
                    break;

                counter.decrement(counter.getDecrementValue(), this.name);
                Thread.sleep(1000);
            }

            System.out.println(String.format("Counter value : '%s' : Thread : '%s' . Finishing", counter.getDecrementValue(), name));

        } catch (InterruptedException ex) {
            ex.printStackTrace();

        } finally {
            if (locker.isLocked()) {
                locker.unlock();
            }
        }

    }

    private boolean checkBreakCondition() {

        locker.lock();

        if (!this.counter.continueProducing) {
            System.out.println(String.format("Останавливаем в %s", name));
            locker.unlock();

            return true;
        }

        locker.unlock();
        return false;
    }
}
