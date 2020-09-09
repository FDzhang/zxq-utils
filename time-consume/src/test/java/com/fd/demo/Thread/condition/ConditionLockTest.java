package com.fd.demo.Thread.condition;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/8 18:12
 */

public class ConditionLockTest {


    public static void main(String[] args) {
        Depot depot = new Depot();

        Producer producer1 = new Producer(depot);
        Producer producer2 = new Producer(depot);
        Consumer consumer1 = new Consumer(depot);

        Executor executor = Executors.newFixedThreadPool(5);
        executor.execute(producer1);
        executor.execute(producer2);
        executor.execute(consumer1);
    }



}
//生产者
class Producer implements Runnable {

    Depot depot;

    public Producer() {
    }

    public Producer(Depot depot) {
        this.depot = depot;
    }

    @Override
    public void run() {
        while (true) {
            depot.prod();
        }
    }
}

//消费者
class Consumer implements Runnable {

    Depot depot;

    public Consumer() {
    }

    public Consumer(Depot depot) {
        this.depot = depot;
    }
    @Override
    public void run() {
        while (true) {
            depot.consum();
        }
    }
}