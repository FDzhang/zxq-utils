package com.fd.demo.timeconsume.annotation;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/20 17:58
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeConsume {
}