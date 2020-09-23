package com.fd.demo;

import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/1 9:36
 */

public class Test1 {

    @Test
    public void test1() {
//        Random random = new Random();
//        random.ints().limit(10).sorted().forEach(System.out::println);
//
//        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
//        // 获取空字符串的数量
//        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
//        System.out.println(count);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        stats.accept(10);
        System.out.println(stats.toString());

        List<Double> numbersD = Arrays.asList(3.2, 2.2, 2.3, 3.4, 7.2, 3.2, 5.2);
        DoubleSummaryStatistics doubleSummaryStatistics = numbersD.stream().mapToDouble(x -> x).summaryStatistics();
        System.out.println(doubleSummaryStatistics.toString());

    }

    @Test
    public void test2() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
//        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        List<Integer> squaresList = numbers.stream()
                .map(i -> i * i)
                .distinct()
                .sorted((x, y) -> y - x)
                .collect(Collectors.toList());
//        squaresList.forEach(System.out::println);
        squaresList.forEach(num -> {
            num++;
            System.out.println(num);
        });

        List<String> strList = Arrays.asList("a", "ba", "bb", "abc", "cbb", "bba", "cab");

        Map<Integer, String> strMap = strList.stream()
                .collect(Collectors.toMap(str -> strList.indexOf(str), str -> str));

        strMap.forEach((key, value) -> {
            System.out.println(key + "::" + value);
        });
    }

    @Test
    public void test3() {

        Student s1 = new Student("aa", 10);
        Student s2 = new Student("bb", 20);
        List<Student> studentList = Arrays.asList(s1, s2);

        studentList.stream()
                .peek(o -> o.setAge(100))
                .forEach(System.out::println);

        studentList.forEach(System.out::println);
//结果：
//        Student{name='aa', age=100}
//        Student{name='bb', age=100}
    }

    @Test
    public void test4() {
        //经过测试，当元素个数小于24时，并行时线程数等于元素个数，当大于等于24时，并行时线程数为16
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

        Integer v = list.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(v);   // 300

        Integer v1 = list.stream().reduce(10, (x1, x2) -> x1 + x2);
        System.out.println(v1);  //310

        Integer v2 = list.stream().reduce(0,
                (x1, x2) -> {
                    System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("stream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v2); // -300

        Integer v3 = list.parallelStream().reduce(0,
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("parallelStream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 + x2;
                });
        System.out.println(v3); //197474048
    }

    @Test
    public void test5() {
        Student s1 = new Student("aa", 10, 1);
        Student s2 = new Student("bb", 20, 2);
        Student s3 = new Student("cc", 10, 3);
        List<Student> list = Arrays.asList(s1, s2, s3);

//装成list
        List<Integer> ageList = list.stream().map(Student::getAge).collect(Collectors.toList()); // [10, 20, 10]

//转成set
        Set<Integer> ageSet = list.stream().map(Student::getAge).collect(Collectors.toSet()); // [20, 10]

//转成map,注:key不能相同，否则报错
        Map<String, Integer> studentMap = list.stream().collect(Collectors.toMap(Student::getName, Student::getAge)); // {cc=10, bb=20, aa=10}

//字符串分隔符连接
        String joinName = list.stream().map(Student::getName).collect(Collectors.joining(",", "(", ")")); // (aa,bb,cc)

//聚合操作
//1.学生总数
        Long count = list.stream().collect(Collectors.counting()); // 3
//2.最大年龄 (最小的minBy同理)
        Integer maxAge = list.stream().map(Student::getAge).collect(Collectors.maxBy(Integer::compare)).get(); // 20
//3.所有人的年龄
        Integer sumAge = list.stream().collect(Collectors.summingInt(Student::getAge)); // 40
//4.平均年龄
        Double averageAge = list.stream().collect(Collectors.averagingDouble(Student::getAge)); // 13.333333333333334
        //Double average = list.stream().mapToInt(Student::getAge).average().getAsDouble();
// 带上以上所有方法
        DoubleSummaryStatistics statistics = list.stream().collect(Collectors.summarizingDouble(Student::getAge));
//        LongSummaryStatistics longSummaryStatistics = list.stream().mapToLong(Student::getAge).summaryStatistics();
        System.out.println("count:" + statistics.getCount() + ",max:" + statistics.getMax() + ",sum:" + statistics.getSum() + ",average:" + statistics.getAverage());

//分组
        Map<Integer, List<Student>> ageMap = list.stream().collect(Collectors.groupingBy(Student::getAge));
//多重分组,先根据类型分再根据年龄分
        Map<Integer, Map<Integer, List<Student>>> typeAgeMap = list.stream().collect(Collectors.groupingBy(Student::getType, Collectors.groupingBy(Student::getAge)));

//分区
//分成两部分，一部分大于10岁，一部分小于等于10岁
        Map<Boolean, List<Student>> partMap = list.stream().collect(Collectors.partitioningBy(v -> v.getAge() > 10));

//规约
        Integer allAge = list.stream().map(Student::getAge).collect(Collectors.reducing(Integer::sum)).get(); //40
//        Integer integer = list.stream().map(Student::getAge).reduce(Integer::sum).get();
    }
}

@Data
class Student {
    private String name;
    private Integer age;
    private Integer type;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, Integer age, Integer type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }
}