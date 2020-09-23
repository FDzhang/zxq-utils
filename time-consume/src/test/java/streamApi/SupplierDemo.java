package streamApi;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/23 17:10
 */

public class SupplierDemo {
    @Test
    public void luckDay() {
        Supplier<String> girlWish = () -> "美女";
        Supplier<String> moneyWish = () -> "钱";

        String girl = magicLamp(girlWish);
        String money = magicLamp(moneyWish);

        System.out.println(girl + "---" + money);
    }

    //你想要什么神灯就给你什么
    public String magicLamp(Supplier<String> wish) {
        return wish.get();
    }
}

//运行结果：
//        美女---钱