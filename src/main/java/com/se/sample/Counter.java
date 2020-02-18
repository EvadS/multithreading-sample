package com.se.sample;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final Integer decrementValue = 8;
    private final Integer incrementValue = 5;
    public volatile Boolean continueProducing = Boolean.TRUE;
    private AtomicInteger counter =  new AtomicInteger(50);

    public synchronized int decrement(int value, String name )  {
        System.out.println(String.format("Decrement. Thread : %s ,  Счетчик   : %s, decrement val : %s ",name,  counter.get(), decrementValue));
        counter.updateAndGet(i -> i - value);

        if (checkCounterValue(name))
            return counter.get();

        System.out.println(String.format(name + " Счетчик уменьшен на  : %s", value));
        System.out.println(String.format(name +  " Значение счетчика    : %s ", counter));
        System.out.println("================================================");

        return counter.get();
    }

    public synchronized Integer increment(Integer value,String name )  {
        System.out.println(String.format("increment. Thread : %s ,  Счетчик  : %s, incr  %s",name,  counter.get(),incrementValue));
        counter.updateAndGet(i -> i + value);

        if (checkCounterValue(name))
            return counter.get();;

        System.out.println(String.format(name + " Счетчик увеличен на : %s ", value));
        System.out.println(String.format(name + " Значение счетчика   : %s ", counter));
        System.out.println("================================================");
        return counter.get();
    }

    private boolean checkCounterValue(String name) {
        if(!continueProducing){
            return  false;
        }
        if (counter.get() < 0 || counter.get() > 100) {
            System.out.println(String.format("Вышли за пределы в : %s, счетчик: %s", name, counter));

            continueProducing = Boolean.FALSE;
            return true;
        }

        return false;
    }

    public synchronized Integer get() {
        return counter.get();
    }

    public Integer getDecrementValue() {
        return decrementValue;
    }

    public Integer getIncrementValue() {
        return incrementValue;
    }

}
