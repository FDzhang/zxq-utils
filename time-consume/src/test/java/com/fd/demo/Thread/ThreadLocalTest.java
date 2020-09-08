package com.fd.demo.Thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/8 17:08
 */

public class ThreadLocalTest {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                test1(test);
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                test1(test);
            }
        });

    }

    private static void test1(ThreadLocalTest test) {
        test.longThreadLocal.set(Thread.currentThread().getId());
        test.stringThreadLocal.set(Thread.currentThread().getName());
        System.out.println(test.longThreadLocal.get());
        System.out.println(test.stringThreadLocal.get());
    }
}
