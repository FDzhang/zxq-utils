package com.fd.demo.utils.java;


import com.fd.demo.utils.bean.Student;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/*
 * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
 *
 * 1.获取构造方法：
 * 		1).批量的方法：
 * 			public Constructor[] getConstructors()：所有"公有的"构造方法
            public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)

 * 		2).获取单个的方法，并调用：
 * 			public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
 * 			public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
 *
 * 			调用构造方法：
 * 			Constructor-->newInstance(Object... initargs)
 */
public class Reflection {

    @Test
    public void test1() throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        //1.加载Class对象
        Class clazz = Class.forName("com.fd.demo.utils.bean.Student");

        Method print = clazz.getMethod("print", String.class);

        Student s = new Student();

        print.invoke(s, "test");
    }


    public static void main(String[] args) throws Exception {

        //1.加载Class对象
        Class clazz = Class.forName("com.fd.demo.utils.bean.Student");

        Method print = clazz.getMethod("print", String.class);

        Student s = new Student();

        print.invoke(s, "test");

        Class<?>[] parameterTypes = print.getParameterTypes();

        Class<?> returnType = print.getReturnType();
        System.out.println(returnType);

        for (Class<?> parameterType : parameterTypes) {
            System.out.println(parameterType);
        }


        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }


        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        //调用构造方法
        Object obj = con.newInstance();
        //	System.out.println("obj = " + obj);
        //	Student stu = (Student)obj;

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);

        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');



    }

}
