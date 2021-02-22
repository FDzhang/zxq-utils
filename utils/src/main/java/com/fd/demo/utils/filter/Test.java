package com.fd.demo.utils.filter;

/**
 * @author ：zxq
 * @date ：Created in 2021/2/22 14:12
 */

public class Test {
    public static void main(String[] args) {
//        String value1 = "https://javaguide.cn/";
//        String value2 = "https://github.com/Snailclimb";
//        MyBloomFilter filter = new MyBloomFilter();
//
//        System.out.println(filter.contains(value1));
//        System.out.println(filter.contains(value2));
//
//        filter.add(value1);
//        filter.add(value2);
//
//        System.out.println(filter.contains(value1));
//        System.out.println(filter.contains(value2));


        Integer value1 = 13423;
        Integer value2 = 22131;

        MyBloomFilter filter = new MyBloomFilter();
        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));

        filter.add(value1);
        filter.add(value2);

        System.out.println(filter.contains(value1));
        System.out.println(filter.contains(value2));
    }
}
