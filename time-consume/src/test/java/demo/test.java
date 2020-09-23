package demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/13 15:56
 */

public class test {


    @Test
    public void getGGCS() {
        String s = FileUtil.readUtf8String("D:\\公共厕所.txt");

        JSONObject jsonObject = JSON.parseObject(s);


        JSONArray content1 = jsonObject.getJSONArray("content");
        int size = content1.size();
        for (int i = 0; i < size; i++) {
            String addr = content1.getJSONObject(i).getString("addr");
            System.out.println(addr);
        }

    }

    @Test
    public void getGGCS2() {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("sid", "1000");
        paramMap.put("d", "mobile");
        paramMap.put("resType", "json");
        paramMap.put("mobile", "1");
        paramMap.put("flag", "callback");
        paramMap.put("encode", "UTF-8");
        paramMap.put("keyword", "焦作市公共厕所");
        paramMap.put("mp", "39.90452,116.40725");
        paramMap.put("map_cpc", "on");
        paramMap.put("relatequery", "on");
        paramMap.put("rqnumber", "14");
        paramMap.put("cityname", "焦作市");
        paramMap.put("ext", "1");
        paramMap.put("qii", true);
        paramMap.put("routeType", 0);
        paramMap.put("routeSelectPOI", 0);
        paramMap.put("business_switch", 1);
        paramMap.put("address_aggregation", 1);
        paramMap.put("hquery", "焦作市公共厕所|公共厕所");
        paramMap.put("_", "1599985139138");
        paramMap.put("number", "200");

        String result3;
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            paramMap.put("batch", i);
            result3 = HttpUtil.get("https://restapi.map.so.com/api/simple", paramMap);
            JSONObject jsonObject = JSON.parseObject(result3);
            JSONArray content1 = jsonObject.getJSONArray("poi");
            int size = content1.size();
            for (int j = 0; j < size; j++) {
                JSONObject poi = content1.getJSONObject(j);
                String adcode = poi.getString("adcode");
                String address = poi.getString("address");
                String area = poi.getString("area");
                String city = poi.getString("city");
                String cityid = poi.getString("cityid");
                String name = poi.getString("name");
                String x = poi.getString("x");
                String y = poi.getString("y");

//                stringBuilder.append("adcode:").append(adcode)
//                        .append(",address:").append(address)
//                        .append(",area:").append(area)
//                        .append(",city:").append(city)
//                        .append(",cityid:").append(cityid)
//                        .append(",x:").append(x)
//                        .append(",y:").append(y)
//                        .append("\n");
                Map<String, Object> row1 = new LinkedHashMap<>();
                row1.put("adcode", adcode);
                row1.put("address", address);
                row1.put("area", area);
                row1.put("city", city);
                row1.put("cityid", cityid);
                row1.put("name", name);
                row1.put("x", x);
                row1.put("y", y);
                rows.add(row1);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/GGCS2.xlsx");
        writer.setColumnWidth(-1, 20);
        writer.setColumnWidth(1, 40);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();

//        FileUtil.writeUtf8String(stringBuilder.toString(),"C:\\Users\\张鑫强\\Desktop\\GGCScsv.txt");


    }

    private HashMap<String, Object> getRequestParam(String keyword, String hquery, String last) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("sid", "1000");
        paramMap.put("d", "mobile");
        paramMap.put("resType", "json");
        paramMap.put("mobile", "1");
        paramMap.put("flag", "callback");
        paramMap.put("encode", "UTF-8");
        paramMap.put("keyword", keyword);
        paramMap.put("mp", "39.90452,116.40725");
        paramMap.put("map_cpc", "on");
        paramMap.put("relatequery", "on");
        paramMap.put("rqnumber", "14");
        paramMap.put("cityname", "焦作市");
        paramMap.put("ext", "1");
        paramMap.put("qii", true);
        paramMap.put("routeType", 0);
        paramMap.put("routeSelectPOI", 0);
        paramMap.put("business_switch", 1);
        paramMap.put("address_aggregation", 1);
        paramMap.put("hquery", hquery);
        paramMap.put("_", last);
        paramMap.put("number", "200");
        return paramMap;
    }

    @Test
    public void getXXZD() {

        HashMap<String, Object> paramMap = getRequestParam("焦作市献血站点", "焦作市献血站点|焦作市公共厕所|公共厕所", "1599990584289");

        String result3;
        ArrayList<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            paramMap.put("batch", i);
            result3 = HttpUtil.get("https://restapi.map.so.com/api/simple", paramMap);
            JSONObject jsonObject = JSON.parseObject(result3);
            JSONArray content1 = jsonObject.getJSONArray("poi");
            int size = content1.size();
            for (int j = 0; j < size; j++) {
                Map<String, Object> row1 = getRow(content1, j);
                rows.add(row1);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/XXZD.xlsx");
        writer.setColumnWidth(-1, 20);
        writer.setColumnWidth(1, 50);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        System.out.println("success");
//        FileUtil.writeUtf8String(stringBuilder.toString(),"C:\\Users\\张鑫强\\Desktop\\GGCScsv.txt");
    }

    @Test
    public void getBWG() {

        HashMap<String, Object> paramMap = getRequestParam("焦作市博物馆", "", "1600005498010");

        String result3;
        ArrayList<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            paramMap.put("batch", i);
            result3 = HttpUtil.get("https://restapi.map.so.com/api/simple", paramMap);
            JSONObject jsonObject = JSON.parseObject(result3);
            JSONArray content1 = jsonObject.getJSONArray("poi");
            int size = content1.size();
            for (int j = 0; j < size; j++) {
                Map<String, Object> row1 = getRow(content1, j);
                rows.add(row1);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/BWG.xlsx");
        writer.setColumnWidth(-1, 20);
        writer.setColumnWidth(1, 50);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        System.out.println("success");
//        FileUtil.writeUtf8String(stringBuilder.toString(),"C:\\Users\\张鑫强\\Desktop\\GGCScsv.txt");
    }

    @Test
    public void getYLY() {

        HashMap<String, Object> paramMap = getRequestParam("焦作市养老院", "焦作市体育设施|焦作市体育|焦作市体育中心|焦作市体育馆|焦作体育馆|焦作市献血站点|焦作市公共厕所|公共厕所", "1600005690027");

        String result3;
        ArrayList<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            paramMap.put("batch", i);
            result3 = HttpUtil.get("https://restapi.map.so.com/api/simple", paramMap);
            JSONObject jsonObject = JSON.parseObject(result3);
            JSONArray content1 = jsonObject.getJSONArray("poi");
            int size = content1.size();
            for (int j = 0; j < size; j++) {
                Map<String, Object> row1 = getRow(content1, j);
                String name = (String) row1.get("name");
                if (!(StrUtil.contains(name, "老") || StrUtil.contains(name, "幸福") || StrUtil.contains(name, "福利"))) {
                    continue;
                }
                rows.add(row1);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/YLY1.xlsx");
        writer.setColumnWidth(-1, 20);
        writer.setColumnWidth(1, 50);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        System.out.println("success");
//        FileUtil.writeUtf8String(stringBuilder.toString(),"C:\\Users\\张鑫强\\Desktop\\GGCScsv.txt");
    }

    private Map<String, Object> getRow(JSONArray content1, int j) {
        JSONObject poi = content1.getJSONObject(j);
        String adcode = poi.getString("adcode");
        String address = poi.getString("address");
        String area = poi.getString("area");
        String city = poi.getString("city");
        String cityid = poi.getString("cityid");
        String name = poi.getString("name");
        String x = poi.getString("x");
        String y = poi.getString("y");

        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("adcode", adcode);
        row1.put("address", address);
        row1.put("area", area);
        row1.put("city", city);
        row1.put("cityid", cityid);
        row1.put("name", name);
        row1.put("x", x);
        row1.put("y", y);
        return row1;
    }

    private Map<String, Object> getRow1(JSONArray content1, int j) {
        JSONObject poi = content1.getJSONObject(j);
        String jbz = poi.getString("jbz");
        String xxbxlx = poi.getString("xxbxlx");
        String xxdz = poi.getString("xxdz");
        String xxmc = poi.getString("xxmc");
        String yzbm = poi.getString("yzbm");

        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("jbz", jbz);
        row1.put("xxbxlx", xxbxlx);
        row1.put("xxdz", xxdz);
        row1.put("xxmc", xxmc);
        row1.put("yzbm", yzbm);
        return row1;
    }


    private HashMap<String, Object> getRequestParam(String keyword) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("draw", " 17");
        paramMap.put("columns[0][data]", " xxmc");
        paramMap.put("columns[0][name]", " ");
        paramMap.put("columns[0][searchable]", " true");
        paramMap.put("columns[0][orderable]", " false");
        paramMap.put("columns[0][search][value]", " ");
        paramMap.put("columns[0][search][regex]", " false");
        paramMap.put("columns[1][data]", " xxdz");
        paramMap.put("columns[1][name]", " ");
        paramMap.put("columns[1][searchable]", " true");
        paramMap.put("columns[1][orderable]", " false");
        paramMap.put("columns[1][search][value]", " ");
        paramMap.put("columns[1][search][regex]", " false");
        paramMap.put("columns[2][data]", " xxbxlx");
        paramMap.put("columns[2][name]", " ");
        paramMap.put("columns[2][searchable]", " true");
        paramMap.put("columns[2][orderable]", " false");
        paramMap.put("columns[2][search][value]", " ");
        paramMap.put("columns[2][search][regex]", " false");
//        paramMap.put("start", " 0");
        paramMap.put("length", 100);
        paramMap.put("search[value]", keyword);
        paramMap.put("search[regex]", " false");
        return paramMap;
    }

    @Test
    public void forTest(){
        String s = "沁阳市、孟州市、博爱县、武陟县、修武县、温县";
        String[] split = s.split("、");
        for (String s1 : split) {
            getGZ(s1);
        }
    }


//    @Test
    public void getYEY(String name) {
        HashMap<String, Object> paramMap = getRequestParam(name+"||null");

        String result3;
        ArrayList<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            paramMap.put("start", i * 100);
            result3 = HttpUtil.post("http://bmfw.haedu.gov.cn/jycx/yeyjbxx/14", paramMap);
            JSONObject jsonObject = JSON.parseObject(result3);
            JSONArray content1 = jsonObject.getJSONArray("data");
            int size = content1.size();
            for (int j = 0; j < size; j++) {
                Map<String, Object> row1 = getRow1(content1, j);
                rows.add(row1);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/"+name+"幼儿园.xlsx");
        writer.setColumnWidth(-1, 20);
        writer.setColumnWidth(2, 40);
        writer.setColumnWidth(3, 40);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        System.out.println("success");
    }

//    @Test
    public void getXX(String name) {
        HashMap<String, Object> paramMap = getRequestParam(name+"||null");


        String result3;
        ArrayList<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            paramMap.put("start", i * 100);
            result3 = HttpUtil.post("http://bmfw.haedu.gov.cn/jycx/xxjbxx/49", paramMap);
            JSONObject jsonObject = JSON.parseObject(result3);
            JSONArray content1 = jsonObject.getJSONArray("data");
            int size = content1.size();
            for (int j = 0; j < size; j++) {
                Map<String, Object> row1 = getRow1(content1, j);
                rows.add(row1);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/"+name+"小学.xlsx");
        writer.setColumnWidth(-1, 20);
        writer.setColumnWidth(2, 40);
        writer.setColumnWidth(3, 40);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        System.out.println("success");
    }

//    @Test
    public void getCZ(String name) {
        HashMap<String, Object> paramMap = getRequestParam(name+"||null");

        String result3;
        ArrayList<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            paramMap.put("start", i * 100);
            result3 = HttpUtil.post("http://bmfw.haedu.gov.cn/jycx/czjbxx/15", paramMap);
            JSONObject jsonObject = JSON.parseObject(result3);
            JSONArray content1 = jsonObject.getJSONArray("data");
            int size = content1.size();
            for (int j = 0; j < size; j++) {
                Map<String, Object> row1 = getRow1(content1, j);
                rows.add(row1);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/"+name+"初中.xlsx");
        writer.setColumnWidth(-1, 20);
        writer.setColumnWidth(2, 40);
        writer.setColumnWidth(3, 40);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        System.out.println("success");
    }

//    @Test
    public void getGZ(String name) {
        HashMap<String, Object> paramMap = getRequestParam(name+"||null");

        String result3;
        ArrayList<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            paramMap.put("start", i * 100);
            result3 = HttpUtil.post("http://bmfw.haedu.gov.cn/jycx/gzjbxx/54", paramMap);
            JSONObject jsonObject = JSON.parseObject(result3);
            JSONArray content1 = jsonObject.getJSONArray("data");
            int size = content1.size();
            for (int j = 0; j < size; j++) {
                Map<String, Object> row1 = getRow1(content1, j);
                rows.add(row1);
            }
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/"+name+"高中.xlsx");
        writer.setColumnWidth(-1, 20);
        writer.setColumnWidth(2, 40);
        writer.setColumnWidth(3, 40);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        System.out.println("success");
    }


    @Test
    public void getBZMD() {
        ArrayList<Map<String, Object>> rows = new ArrayList<>();
        long startTime = System.nanoTime();

        for (int i = 1; i <= 52; i++) {
            String listContent = HttpUtil.get("http://mzj.jiaozuo.gov.cn/jzdb.asp?leftid=27&page=" + i);
            List<String> trList = ReUtil.findAll("<tr bgcolor=\"#eeeeee\">(.*?)</tr>", listContent, 1);
            //请求列表页
            //使用正则获取所有标题
            for (String tr : trList) {
                List<String> all = ReUtil.findAll("<td align=\"center\">(.*?)</td>", tr, 1);
                String[] strings1 = new String[all.size()];
                all.toArray(strings1);
                Map<String, Object> row2 = getRow2(strings1);
                rows.add(row2);
            }
        }
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("request: " + millis + "ms");
//        writeToExcel(rows,"BZMD");
        writeToBigExcel(rows, "BZMD");
    }

    private void writeToExcel(ArrayList<Map<String, Object>> rows, String fileName) {
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/" + fileName + ".xlsx");
        writer.setColumnWidth(-1, 20);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        System.out.println("success");
    }

    private void writeToBigExcel(ArrayList<Map<String, Object>> rows, String fileName) {
        // 通过工具类创建writer
        long startTime = System.nanoTime();

        ExcelWriter writer = ExcelUtil.getBigWriter("d:/" + fileName + ".xlsx");
        writer.setColumnWidth(-1, 20);
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(millis + "ms");
        System.out.println("success");
    }

    private Map<String, Object> getRow2(String[] content1) {

        String a1 = content1[0];
        String a2 = content1[1];
        String a3 = content1[2];
        String a4 = content1[3];
        String a5 = content1[4];
        String a6 = content1[5];

        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("行政区划", a1);
        row1.put("乡", a2);
        row1.put("姓名", a3);
        row1.put("性别", a4);
        row1.put("身份证", a5);
        row1.put("城乡性质", a6);
        return row1;
    }



}
