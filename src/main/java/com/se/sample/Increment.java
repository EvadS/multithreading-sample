package com.se.sample;

import java.util.concurrent.locks.ReentrantLock;

public class Increment implements  Runnable {

    private Counter counter ;
    private String name;
    ReentrantLock locker;

    public Increment(String name, Counter counter, ReentrantLock locker ) {
        this.counter = counter;
        this.name = name;
        this.locker = locker;
    }

    @Override
    public void run() {
        try
        {

            while(true)
            {
               // locker.lock();
                if(!this.counter.continueProducing){
                    System.out.println(String.format("Останавливаем в %s", name));
                //    locker.unlock();
                    break;
                }

               // locker.unlock();
                counter.increment(counter.getIncrementValue(),this.name);

                Thread.sleep(1000);
            }

            System.out.println(name + " finished its job; terminating...");
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }
}
