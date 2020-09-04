package com.fd.demo.Thread;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/4 16:36
 */

public class TestWait {
    int i = 10;
    boolean isTrue = false;

    public static void main(String[] args) {
        TestWait testWait = new TestWait();
        ThreadA a = new ThreadA(testWait);
        ThreadB b = new ThreadB(testWait);
        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);
        threadA.start();
        threadB.start();
    }
}

class ThreadA implements Runnable {
    private final TestWait testWait;

    public ThreadA(TestWait testWait) {
        this.testWait = testWait;
    }

    @Override
    public void run() {
        while (testWait.i > 0) {
            synchronized (testWait) {
                if (!testWait.isTrue) {
                    try {
                        testWait.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("奇数：" + testWait.i);
                    testWait.i--;
                    testWait.isTrue = false;
                    testWait.notifyAll();
                }
            }
        }
    }
}

class ThreadB implements Runnable {
    private final TestWait testWait;

    public ThreadB(TestWait testWait) {
        this.testWait = testWait;
    }

    @Override
    public void run() {
        while (testWait.i > 0) {
            synchronized (testWait) {
                if (testWait.isTrue) {
                    try {
                        testWait.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("偶数：" + testWait.i);
                    testWait.i--;
                    testWait.isTrue = true;
                    testWait.notifyAll();
                }
            }
        }
    }
}
