package com.fd.demo.timeconsume.Thread;


/**
 * @author ：zxq
 * @date ：Created in 2020/9/3 10:29
 */

public class PrintNum extends Thread {

    public PrintNum() {
    }

    public PrintNum(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}