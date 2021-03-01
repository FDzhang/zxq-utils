package com.fd.demo.utils.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：zxq
 * @date ：Created in 2021/2/24 9:38
 */

public class Base {



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student s1 = new Student("小张");
        Student s2 = new Student("小李");
        swap(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
    }

    public static void swap(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }


    public static void swap(Boolean x, Integer y) {
    }


    public static void swap(Integer y, Boolean x) {
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Student{
        private String name;
    }
}