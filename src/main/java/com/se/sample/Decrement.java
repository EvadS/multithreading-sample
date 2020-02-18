package com.se.sample;

public class Decrement implements Runnable {

    private Counter counter;
    private String name;

    public Decrement(String name, Counter counter) {
        this.counter = counter;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (this.counter.continueProducing) {
                System.out.println("Decrement counter : " + counter.getDecrementValue());
                counter.decrement(counter.getDecrementValue());
                Thread.sleep(1000);
            }

            System.out.println(String.format("Counter value : '%s' : Thread : '%s' . Finishing", counter.getDecrementValue(),  name  ));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
