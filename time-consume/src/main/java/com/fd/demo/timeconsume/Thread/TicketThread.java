package com.fd.demo.timeconsume.Thread;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/3 10:34
 */

public class TicketThread extends Thread {

    public TicketThread() {
    }

    public TicketThread(String name) {
        super(name);
    }

    private int ticket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (TicketThread.class) {
                if (this.ticket > 0) {
                    try {
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName() + "卖票---->" + (this.ticket--));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] arg){
        TicketThread t1 = new TicketThread();
        new Thread(t1,"线程1").start();
        new Thread(t1,"线程2").start();
        //经常看到晚上说thread不能共享资源，这个用例子说明thread同样可以共享资源。
        //为什么它也能共享呢？因为Thread本来就是实现了Runnable，包含Runnable的功能是很正常。
    }
}
