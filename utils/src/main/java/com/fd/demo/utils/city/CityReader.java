package com.fd.demo.utils.city;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/27 17:56
 */

public class CityReader {

    public static void main(String[] args) {
        // 数据来源 官网： http://www.mca.gov.cn/article/sj/xzqh
        ExcelReader reader = ExcelUtil.getReader("D:/城市.xlsx");
        List<CityTable> all = reader.readAll(CityTable.class);

        FileWriter writer = new FileWriter("D:/top.json");

        FileWriter writer2 = new FileWriter("D:/second.json");

        FileWriter writer3 = new FileWriter("D:/third.json");

        CityTable top = new CityTable();
        CityTable second = new CityTable();

        for (CityTable c : all) {
            if (c.getCity_code().endsWith("0000")) {
                City city = new City();
                city.setCity(c.getCity().trim());
                city.setCity_code(c.getCity_code());
                city.setParent_city("中国");
                city.setParent_city_code("000000");
                city.setLevel(1);

                System.out.println(JSON.toJSONString(city));

                writer.append(JSON.toJSONString(city));

                top.setCity(c.getCity().trim());
                top.setCity_code(c.getCity_code());

                if (!("上海市".equals(c.getCity())
                        || "天津市".equals(c.getCity())
                        || "北京市".equals(c.getCity())
                        || "重庆市".equals(c.getCity()))) {
                    continue;
                }
            }

            if (c.getCity_code().endsWith("00")) {
                City city = new City();
                city.setCity(c.getCity().trim());
                city.setCity_code(c.getCity_code());
                city.setParent_city(top.getCity().trim());
                city.setParent_city_code(top.getCity_code());
                city.setLevel(2);

                writer2.append(JSON.toJSONString(city));

                second.setCity(c.getCity().trim());
                second.setCity_code(c.getCity_code());

                continue;
            }

            City city = new City();
            city.setCity(c.getCity().trim());
            city.setCity_code(c.getCity_code());
            city.setParent_city(second.getCity());
            city.setParent_city_code(second.getCity_code());
            city.setLevel(3);

            writer3.append(JSON.toJSONString(city));

        }

        System.out.println("finish");
        System.out.println(all.get(0));

    }
}
