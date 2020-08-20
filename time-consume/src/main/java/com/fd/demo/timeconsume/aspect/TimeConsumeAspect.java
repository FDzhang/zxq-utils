package com.fd.demo.timeconsume.aspect;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/20 18:00
 */
import com.fd.demo.timeconsume.annotation.TimeConsume;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
@Component
public class TimeConsumeAspect {
    @Around("@annotation(timeConsume)")
    public Object computeTimeConsume(ProceedingJoinPoint proceedingJoinPoint, TimeConsume timeConsume) throws Throwable {
        long startTime = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        Signature signature = proceedingJoinPoint.getSignature();
        Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
        log.info("spend time : {} ms --- ({}.{})",  millis, targetClass.getName(), signature.getName());
        return result;
    }
}