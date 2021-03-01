package com.fd.demo.utils.proxy;

/**
 * @author ：zxq
 * @date ：Created in 2021/2/25 17:56
 */

public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String msg) {
        System.out.println("send msg : " + msg);
        return msg;
    }

    @Override
    public String check(String name) {
        System.out.println("check name : " + name);

        return name;
    }
}
