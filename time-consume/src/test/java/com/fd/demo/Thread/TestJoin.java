package com.fd.demo.Thread;

/**
 * 题目：假设有两个线程，一个是线程 A，另一个是线程 B，两个线程分别依次打印 1-3 三个数字即可。我们希望 B 在 A 全部打印完后再开始打印。
 *
 * 关键方法：join()。
 *
 * @author ：zxq
 * @date ：Created in 2020/9/4 16:27
 */

public class TestJoin {

    public static void main(String[] args) {
        demo();
    }

    private static void demo(){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                    printX("A");
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B 开始等待 A");
                try {
                    a.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printX("B");
            }
        });
        b.start();
        a.start();
    }

    private static void printX(String name){
        for (int i = 1; i < 4; i++) {
            System.out.println(name + " -- " + i);
        }
    }
}
