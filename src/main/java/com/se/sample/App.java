package com.se.sample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
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
            int cores = Runtime.getRuntime().availableProcessors();
            ExecutorService executorService = Executors.newFixedThreadPool(cores);


            executorService.submit(new Decrement("Decrement 1", counter, locker));
            executorService.submit(new Increment("Increment 2", counter, locker));

            executorService.submit(new Decrement("Decrement 3", counter, locker));
            executorService.submit(new Increment("Increment 3", counter, locker));

            executorService.submit(new Increment("Increment 1", counter, locker));
            executorService.submit(new Decrement("Decrement 2", counter, locker));

            executorService.submit(new Increment("Increment 2", counter, locker));
            executorService.submit(new Decrement("Decrement 4", counter, locker));
            executorService.submit(new Increment("Increment 4", counter, locker));

            executorService.submit(new Decrement("Decrement 5", counter, locker));
            executorService.submit(new Increment("Increment 5", counter, locker));

            executorService.submit(new Decrement("Decrement 6", counter, locker));
            executorService.submit(new Increment("Increment 6", counter, locker));

            executorService.shutdown();


            try {
                //ждем 10 мин - иначе принудительно завершаем
                if (!executorService.awaitTermination(10, TimeUnit.MINUTES)) {

                    executorService.shutdownNow();
                }
            } catch (InterruptedException ex) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
