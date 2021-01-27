package com.fd.demo.utils.thread;

import cn.hutool.core.thread.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/27 15:53
 */

public class ThreadTest implements Runnable {

    public String print() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
        return null;
    }

    public static void main(String[] args) {
        ThreadUtil.execAsync(new ThreadTest());

        System.out.println("end");
    }

    @Override
    public void run() {
        print();
    }
}
