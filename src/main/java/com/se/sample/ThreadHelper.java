package com.se.sample;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadHelper {

    // one place for check top and bottom line per counter
    public static boolean checkBreakCondition(ReentrantLock locker, String name, boolean continueProducing) {
        locker.lock();

        if (!continueProducing) {
            System.out.println(String.format("Останавливаем в %s", name));
            locker.unlock();

            return true;
        }

        locker.unlock();

        return false;
    }
}