package com.se.sample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try
        {
            ReentrantLock locker = new ReentrantLock(); // создаем заглушку


            Counter counter = new Counter();
            ExecutorService threadPool = Executors.newFixedThreadPool(3);

            threadPool.execute(new Decrement("Decrement 1", counter,locker));
            threadPool.execute(new Increment("Increment 2", counter,locker));

            threadPool.execute(new Decrement("Decrement 3", counter,locker));
            threadPool.execute(new Increment("Increment 3", counter,locker));

            threadPool.execute(new Increment("Increment 1", counter,locker));
            threadPool.execute(new Decrement("Decrement 2", counter,locker));

            threadPool.execute(new Increment("Increment 2", counter,locker));
            threadPool.execute(new Decrement("Decrement 4", counter,locker));
            threadPool.execute(new Increment("Increment 4", counter,locker));

            threadPool.execute(new Decrement("Decrement 5", counter,locker));
            threadPool.execute(new Increment("Increment 5", counter,locker));

            threadPool.execute(new Decrement("Decrement 6", counter,locker));
            threadPool.execute(new Increment("Increment 6", counter,locker));

            threadPool.shutdown();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
