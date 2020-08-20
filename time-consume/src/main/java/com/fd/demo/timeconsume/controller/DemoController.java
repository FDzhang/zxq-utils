package com.fd.demo.timeconsume.controller;

import com.fd.demo.timeconsume.annotation.TimeConsume;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/20 18:07
 */
@RestController
public class DemoController {

    @TimeConsume
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
