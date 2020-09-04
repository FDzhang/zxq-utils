package com.fd.demo.Thread;

/*CountdownLatch基本用法是:
 * 1)创建一个计数器，设置初始值，CountdownLatch countDownLatch = new CountDownLatch(3);
 * 2)在 等待线程 里调用 countDownLatch.await() 方法，进入等待状态，直到计数值变成 0；
 * 3)在 其他线程 里，调用 countDownLatch.countDown() 方法，该方法会将计数值减小 1；
 * 4)当 其他线程 的 countDown() 方法把计数值变成 0 时，等待线程 里的 countDownLatch.await() 立即退出，继续执行下面的代码。
 */

import java.util.concurrent.CountDownLatch;

/**
 * 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的。
 *
 * @author ：zxq
 * @date ：Created in 2020/9/4 17:10
 */

public class TestCoundownLatch {
    public static void main(String[] args) {
        runDAfterABC();
    }

    private static void runDAfterABC() {
        int worker = 3;
        CountDownLatch countDownLatch = new CountDownLatch(worker);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("D开始工作前等待ABC工作完成");
                try {
                    //因为worker初始值为3，所以在不等于0之前一直处于等待状态
                    countDownLatch.await();
                    System.out.println("ABC工作完成，D开始工作...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("D完成工作");
            }
        }).start();

        for (char i = 'A'; i <= 'C'; i++) {
            final String tn = String.valueOf(i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(tn + "正在工作...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tn + "完成工作");
                    //每调用一次worker值减一
                    countDownLatch.countDown();
                }
            }).start();
        }
    }
}
