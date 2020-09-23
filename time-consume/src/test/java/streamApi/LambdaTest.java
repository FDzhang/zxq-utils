package streamApi;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/23 17:01
 */

public class LambdaTest {

    @Test
    public void cutHand() {
        Goods goods = new Goods("口红", 288.0);

        //土豪
        spentMoney(goods, (g) -> System.out.println("消费" + g.getCost() + "元"));

        System.out.println("-------------------贫富分割线--------------------");

        //屌丝
        spentMoneyAndLog(goods, (g) -> System.out.println("消费" + g.getCost() + "元"));
    }

    //任性地花
    public void spentMoney(Goods goods, Consumer<Goods> consumer) {
        consumer.accept(goods);
    }

    //花一笔记一笔
    public void spentMoneyAndLog(Goods goods, Consumer<Goods> consumer) {
        Consumer<Goods> logConsumer = (g) -> System.out.println("买" + g.getGoodsName() + "用了" + g.getCost() + "元！");
        consumer.andThen(logConsumer).accept(goods);
    }
//
//    运行结果：
//    消费288.0元
//-------------------贫富分割线--------------------
//    消费288.0元
//    买口红用了288.0元！

}

