# multithreading-sample
simple project for demostrate how to use some thread in one object

## Compile 
mvn clean package 

## Run 
java -jar target/counter.jar


-----------------------------------------
Create a variable(counter) that can be shared by all the clients, the initial value of the counter is 50.

The producer threads will increase the value of the counter while the consumer threads will decrease it.

Print in the console the current value of the counter when it changes and print which producer/consumer is responsible for the change.

The threads will run in parallel and continue until the counter reaches 0 or 100. Persist in the database the timestamp when the counter reaches 0 or 100.


TODO list 
https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html#AtomicInteger--