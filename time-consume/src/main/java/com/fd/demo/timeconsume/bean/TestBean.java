package com.fd.demo.timeconsume.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/28 10:49
 */
@Data
public class TestBean {
    private String name;
    private int age;
    private double score;
    private boolean isPass;
    private Date examDate;
}
