package com.fd.demo.Thread;

/* CyclicBarrier 基本用法
 * 1)先创建一个公共 CyclicBarrier 对象，设置 同时等待 的线程数，CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
 * 2)这些线程同时开始自己做准备，自身准备完毕后，需要等待别人准备完毕，这时调用 cyclicBarrier.await(); 即可开始等待别人；
 * 3)当指定的 同时等待 的线程数都调用了 cyclicBarrier.await();时，意味着这些线程都准备完毕好，然后这些线程才 同时继续执行。
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 三个运动员各自准备，等到三个人都准备好后，再一起跑
 *
 * @author ：zxq
 * @date ：Created in 2020/9/4 17:36
 */

public class TestCyclicBarrier {
    public static void main(String[] args) {
        runABCWhenAllReady();
    }

    private static void runABCWhenAllReady() {
        int runner = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);

        for (char runnerName = 'A'; runnerName <= 'C'; runnerName++) {
            final String rN = String.valueOf(runnerName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(rN + " 已经准备好，等待其它线程准备");
                        cyclicBarrier.await(); // 当前运动员准备完毕，等待别人准备好
                    } catch (BrokenBarrierException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(rN + "开始跑"); // 所有运动员都准备好了，一起开始跑
                }
            }).start();
        }
    }
}
