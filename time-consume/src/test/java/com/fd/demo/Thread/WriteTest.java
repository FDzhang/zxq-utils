package com.fd.demo.Thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/8 10:05
 */

public class WriteTest {
    private final ReadWriteLock rwl = new ReentrantReadWriteLock();


    public static void main(String[] args) {
        final WriteTest test = new WriteTest();

        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();
    }

    public void get(Thread thread) {
        //这里放读锁
        rwl.readLock().lock();
        try {
            for(int i=0;i<3;i++){
                System.out.println(thread.getName()+"正在进行读操作");
            }
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            //这里释放锁
            rwl.readLock().unlock();
        }
    }
}
