package com.fd.demo.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/7 17:48
 */

public class ReenTrantLockTest {

    private List<Integer> arrayList = new ArrayList<>();
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final ReenTrantLockTest test = new ReenTrantLockTest();

        new Thread() {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

    }

    public void insert(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "得到了锁");
                for (int i = 0; i < 5; i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {

            } finally {
                System.out.println(thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }


}
