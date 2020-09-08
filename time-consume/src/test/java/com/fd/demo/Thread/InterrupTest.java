package com.fd.demo.Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/7 18:39
 */

public class InterrupTest {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args)  {
        InterrupTest test = new InterrupTest();
        MyThread thread0 = new MyThread(test);
        MyThread thread1 = new MyThread(test);
        thread0.start();
        thread1.start();

        thread1.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException{
        //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        lock.lockInterruptibly();
        try {
            System.out.println(thread.getName()+"得到了锁");
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }

}
class MyThread extends Thread {
    private InterrupTest test = null;
    public MyThread(InterrupTest test) {
        this.test = test;
    }
    @Override
    public void run() {

        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}