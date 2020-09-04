package com.fd.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/4 15:13
 */

class ThreadTesterA implements Runnable{
    private int counter;
    @Override
    public void run() {
        while (counter <= 10) {
            System.out.println("Counter = " + counter + " ");
            counter++;
        }
    }
}
class ThreadTesterB implements Runnable {
    private int i;
    public void run() {
        while (i <= 10) {
            System.out.println("i = " + i + " ");
            i++;
        }
    }
}
public class JoinTest {
    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new ThreadTesterA());
        Thread t2 = new Thread(new ThreadTesterB());
        t1.start();
        t1.join();
        t2.start();

    }
}
