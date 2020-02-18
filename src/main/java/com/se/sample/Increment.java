package com.se.sample;

public class Increment implements  Runnable {

    private Counter counter ;
    private String name;

    public Increment(String name,Counter counter) {
        this.counter = counter;
        this.name = name;
    }

    @Override
    public void run() {
        try
        {
            while(this.counter.continueProducing)
            {
                System.out.println("Counter value : " + counter.get() + ",Increment produced: " + counter.getIncrementValue());
                counter.increment(counter.getIncrementValue());
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
