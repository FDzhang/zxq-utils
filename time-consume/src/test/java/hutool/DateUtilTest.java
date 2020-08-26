package hutool;

import cn.hutool.core.date.*;
import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/26 9:22
 */

public class DateUtilTest {

    @Test
    public void Test() {
        //当前时间
        Date date = DateUtil.date();
        //当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        //当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();

        /**
         * 字符串转日期
         * DateUtil.parse方法会自动识别一些常用格式，包括：
         *
         * yyyy-MM-dd HH:mm:ss
         * yyyy-MM-dd
         * HH:mm:ss
         * yyyy-MM-dd HH:mm
         * yyyy-MM-dd HH:mm:ss.SSS
         */
        String dateStr = "2017-03-01";
        Date date1 = DateUtil.parse(dateStr);


        String dateStr4 = "2017-03-01";
        Date date4 = DateUtil.parse(dateStr, "yyyy-MM-dd");

        String dateStr5 = "2017-03-01";
        Date date5 = DateUtil.parse(dateStr);

        //结果 2017/03/01
        String format = DateUtil.format(date, "yyyy/MM/dd");

        //常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date);

        //结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date);

        //结果：00:00:00
        String formatTime = DateUtil.formatTime(date);

        Date date6 = DateUtil.date();
        //获得年的部分
        DateUtil.year(date);
        //获得月份，从0开始计数
        DateUtil.month(date);
        //获得月份枚举
        DateUtil.monthEnum(date);
        //.....

        String dateStr7 = "2017-03-01 22:33:23";
        Date date7 = DateUtil.parse(dateStr);

        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);

        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);

        String dateStr8 = "2017-03-01 22:33:23";
        Date date8 = DateUtil.parse(dateStr);

        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);

        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);

        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);

        //昨天
        DateUtil.yesterday();
        //明天
        DateUtil.tomorrow();
        //上周
        DateUtil.lastWeek();
        //下周
        DateUtil.nextWeek();
        //上个月
        DateUtil.lastMonth();
        //下个月
        DateUtil.nextMonth();

        String dateStr1 = "2017-03-01 22:33:23";
        Date date9 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 23:33:23";
        Date date10 = DateUtil.parse(dateStr2);

        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);

        //Level.MINUTE表示精确到分
        String formatBetween = DateUtil.formatBetween(betweenDay, BetweenFormater.Level.MINUTE);
        //输出：31天1小时
        Console.log(formatBetween);

        // "摩羯座"
        String zodiac = DateUtil.getZodiac(Month.JANUARY.getValue(), 19);

        // "狗"
        String chineseZodiac = DateUtil.getChineseZodiac(1994);

        //年龄
        System.out.println(DateUtil.ageOfNow("19900130"));

        //是否闰年
        DateUtil.isLeapYear(2017);
    }
}
