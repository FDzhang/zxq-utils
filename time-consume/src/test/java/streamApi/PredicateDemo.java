package streamApi;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/23 17:25
 */
public class PredicateDemo {

    @Test
    public void test(){
        Predicate<String> p1=(t)->t.equals("nice");
        Predicate<String> p2=(t)->t.endsWith("e");

        boolean result1 = p1.test("nice");
        System.out.println(result1);
        //表示 ! p1.test()
        boolean result2 = p1.negate().test("nice");
        System.out.println(result2);
        //表示p1.test(arg) && p2.test(arg)
        boolean result3 = p1.and(p2).test("nice");
        System.out.println(result3);
        //表示p1.test(arg) || p2.test(arg)
        boolean result4 = p1.or(p2).test("good");
        System.out.println(result4);
        //获取到一个Predicate实例p，p.test(arg) 表示targetRef 是否等于arg
        Predicate<String> p = Predicate.isEqual("当这个参数为null，使用==判断，否则使用equal方法判断");
        boolean result5 = p.test("end");
        System.out.println(result5);
    }
}

//运行结果：
//        true
//        false
//        true
//        false
//        false
