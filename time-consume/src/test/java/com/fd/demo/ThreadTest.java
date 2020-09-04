package com.fd.demo;

import com.fd.demo.timeconsume.Thread.CallableImpl;
import com.fd.demo.timeconsume.Thread.PrintNum;
import com.fd.demo.timeconsume.Thread.TicketThread;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/3 10:24
 */

public class ThreadTest extends Thread {

    @Test
    public void test1(){
        PrintNum p1 = new PrintNum("线程1");
        PrintNum p2 = new PrintNum("线程2");
        p1.start();
        p2.start();
    }

    public static void test2(){
        TicketThread t1 = new TicketThread();


        new Thread(t1,"线程1").start();

        System.out.println("fasdfasdf");

        t1.run();

        new Thread(t1,"线程2").start();

        System.out.println("1111");

        System.out.println("2222");


    }

    @Test
    public void test3(){
        test2();
    }

    public static void main(String[] args) {
        test2();
    }

    @Test
    public void test4(){
        Callable<String> callable = new CallableImpl("my callable test!");
        FutureTask<String> task = new FutureTask<>(callable);

        long beginTime = System.currentTimeMillis();

        // 创建线程
        new Thread(task).start();

        // 调用get()阻塞主线程，反之，线程不会阻塞
        String result = null;
        try {
             result = task.get();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        long endTime = System.currentTimeMillis();

        System.out.println("hello : " + result);
        System.out.println("cast : " + (endTime - beginTime) / 1000 + " second!");
    }

}
