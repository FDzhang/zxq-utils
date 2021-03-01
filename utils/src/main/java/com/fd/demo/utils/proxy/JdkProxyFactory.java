package com.fd.demo.utils.proxy;

import java.lang.reflect.Proxy;

/**
 * @author ：zxq
 * @date ：Created in 2021/2/25 18:00
 */

public class JdkProxyFactory {

    public static Object getProxy(Object target){

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new DebugInvocationHandler(target)
        );
    }
}
