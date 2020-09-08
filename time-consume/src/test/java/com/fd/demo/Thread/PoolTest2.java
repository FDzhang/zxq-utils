package com.fd.demo.Thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/8 16:00
 */

public class PoolTest2 implements Runnable {
    @Test
    public void testThreadPool() {
        Runtime run = Runtime.getRuntime();
        run.gc();
        Long freeMemory = run.freeMemory();
        Long proTime = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            new Thread(new PoolTest2()).start();
        }
        System.out.println("独立创建" + 10000 + "个线程需要的内存空间" + (freeMemory - run.freeMemory()));
        System.out.println("独立创建" + 10000 + "个线程需要的系统时间" + (System.currentTimeMillis() - proTime));

        System.out.println("---------------------------------");
        Runtime run2 = Runtime.getRuntime();//当前程序运行对象
        run2.gc();//调用垃圾回收机制，减少内存误差
        Long freeMemory2 = run.freeMemory();//获取当前空闲内存
        Long proTime2 = System.currentTimeMillis();

        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10000; i++) {
            service.execute(new PoolTest2());
        }

        System.out.println("线程池创建" + 10000 + "个线程需要的内存空间" + (freeMemory2 - run.freeMemory()));
        service.shutdown();

        System.out.println("线程池创建" + 10000 + "个线程需要的系统时间" + (System.currentTimeMillis() - proTime2));
    }


    @Override
    public void run() {

    }
}

