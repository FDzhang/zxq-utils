package com.fd.demo.timeconsume.Thread;

import java.util.concurrent.Callable;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/3 10:50
 */

public class CallableImpl implements Callable<String> {

    private String acceptStr;

    public CallableImpl(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return this.acceptStr + " append some chars and return it!";
    }
}
