package com.se.sample;

public class Counter {

    private final Integer decrementValue = 10;
    private final Integer incrementValue = 15;
    public volatile Boolean continueProducing = Boolean.TRUE;
    private Integer counter = 50;

    public synchronized Integer decrement(Integer value) {

        if (counter < 0 || counter > 100) {
            continueProducing = Boolean.FALSE;
            return counter;
        }

        counter -= value;
        System.out.println(String.format("Счетчик уменьшен на  : %s", value));
        System.out.println(String.format("Значение счетчика    : %s ", counter));
        System.out.println("================================================");

        return counter;
    }

    public synchronized Integer increment(Integer value) {
        if (counter < 0 || counter > 100) {
            continueProducing = Boolean.FALSE;
            return counter;
        }
        counter += value;
        System.out.println(String.format("Счетчик увеличен на : %s ", value));
        System.out.println(String.format("Значение счетчика   : %s ", counter));
        System.out.println("================================================");
        return counter;
    }

    public synchronized Integer get() {
        return counter;
    }

    public Integer getDecrementValue() {
        return decrementValue;
    }

    public Integer getIncrementValue() {
        return incrementValue;
    }

}
