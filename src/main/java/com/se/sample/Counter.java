package com.se.sample;

public class Counter {

    private final Integer decrementValue = 8;
    private final Integer incrementValue = 5;
    public volatile Boolean continueProducing = Boolean.TRUE;
    private Integer counter = 50;

    public synchronized Integer decrement(Integer value, String name )  {

        counter -= value;

        if (checkCounterValue(name))
            return counter;

        System.out.println(String.format("Счетчик уменьшен на  : %s", value));
        System.out.println(String.format("Значение счетчика    : %s ", counter));
        System.out.println("================================================");

        return counter;
    }

    public synchronized Integer increment(Integer value,String name )  {

        counter += value;

        if (checkCounterValue(name))
            return counter;

        System.out.println(String.format("Счетчик увеличен на : %s ", value));
        System.out.println(String.format("Значение счетчика   : %s ", counter));
        System.out.println("================================================");
        return counter;
    }

    private boolean checkCounterValue(String name) {
        if (counter < 0 || counter > 100) {
            System.out.println(String.format("Вышли за пределы в : %s, счетчик: %s", name, counter));

            continueProducing = Boolean.FALSE;
            return true;
        }

        return false;
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
