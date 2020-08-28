package com.fd.demo.timeconsume.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/27 17:09
 */
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = -883870621754484820L;
    private String name;
    private Integer age;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birth;
}
