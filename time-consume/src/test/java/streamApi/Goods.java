package streamApi;

import lombok.Data;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/23 17:13
 */
@Data
public class Goods {
    private String goodsName;
    private Double cost;

    public Goods(String goodsName, Double cost) {
        this.goodsName = goodsName;
        this.cost = cost;
    }
}
