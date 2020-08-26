package hutool;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/25 23:18
 */

public class ChineseDateTest {

    @Test
    public void test() {
        ChineseDate date = new ChineseDate(DateUtil.parseDate("2020-01-25"));
        // 一月
        date.getChineseMonth();
        // 正月
        date.getChineseMonthName();
        // 初一
        date.getChineseDay();
        // 庚子
        date.getCyclical();
        // 鼠
        date.getChineseZodiac();
        // 春节
        date.getFestivals();
        // 庚子鼠年 正月初一
        date.toString();

        System.out.println(date);
    }
}
