package com.se.sample;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final Integer decrementValue = 3;
    private final Integer incrementValue = 2;
    private final int UPPER_LINE = 100;
    private final int BOTTOM_LINE = 0;
    public volatile Boolean continueProducing = Boolean.TRUE;
    private AtomicInteger counter = new AtomicInteger(50);

    public synchronized int decrement(int value, String name) {
        if (checkCounterValue(name))
            return counter.get();

        System.out.println(String.format("Decrement. Thread : %s ,Счетчик  : %s, уменьшаем на: %s ", name, counter.get(), decrementValue));

        counter.updateAndGet(i -> i - value);
        System.out.println(String.format(name + "== Счетчик уменьшен на  : %s. Значение счетчика    : %s", value, counter));

        if (checkCounterValue(name))
            return counter.get();

        return counter.get();
    }

    public synchronized int increment(Integer value, String name) {

        if (checkCounterValue(name))
            return counter.get();

        System.out.println(String.format("increment. Thread : %s ,  Счетчик  : %s, увеличиваем на :  %s", name, counter.get(), incrementValue));
        counter.updateAndGet(i -> i + value);

        System.out.println(String.format(name + "== Счетчик увеличен на : %s. Значение счетчика   : %s ", value, counter));

        if (checkCounterValue(name))
            return counter.get();

        return counter.get();
    }

    private synchronized boolean checkCounterValue(String name) {
        if (!continueProducing) {
            return false;
        }

        if (counter.get() < BOTTOM_LINE || counter.get() > UPPER_LINE) {
            System.out.println(String.format("Вышли за пределы в : %s, счетчик: %s", name, counter));

            continueProducing = Boolean.FALSE;
            return true;
        }

        return false;
    }


    public Integer getDecrementValue() {
        return decrementValue;
    }

    public Integer getIncrementValue() {
        return incrementValue;
    }

}
