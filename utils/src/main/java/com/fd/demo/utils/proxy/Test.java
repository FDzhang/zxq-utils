package com.fd.demo.utils.proxy;

/**
 * @author ：zxq
 * @date ：Created in 2021/2/25 18:02
 */

public class Test {
    public static void main(String[] args) {

        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
        smsService.check("123");
    }
}
