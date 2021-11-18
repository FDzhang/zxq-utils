package com.fd.demo.utils.reflect;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;


/**
 * @author zhangxinqiang
 * @create 2021/11/18 15:47
 */
class SetValueTest {

    @Data
    static class User{
        private String name;
        private Integer age;
    }

    @Test
    void userTest(){
        User u = new User();

        System.err.println(u);
        buildUser(u);
        System.err.println(u);
    }

    public <T> void buildUser(T entity){
        Class<?> clazz = entity.getClass();
        try {
            // 获取字段
            Field name = clazz.getDeclaredField("name");
            Field age = clazz.getDeclaredField("age");

            // 开通权限
            name.setAccessible(true);
            age.setAccessible(true);

            // 赋值，不光可以用set 还可以用setInteger等，但是string没提供，用obj就可以
            name.set(entity, "张三");
            age.set(entity, 123);
        } catch (Exception e) { // 此异常为 实体内字段不存在
            e.printStackTrace();
        }
    }
}