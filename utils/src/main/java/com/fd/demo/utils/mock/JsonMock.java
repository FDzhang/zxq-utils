package com.fd.demo.utils.mock;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/17 15:13
 */

public class JsonMock {

    public static JSONObject mockJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "测试_Test-123");
        jsonObject.put("like", mockList());
        jsonObject.put("birth", new Date(1608281243000L));
        jsonObject.put("emptyArray", new ArrayList<>());
        jsonObject.put("emptyStr", "");
        jsonObject.put("empty", null);
        return jsonObject;
    }

    public static List<String> mockList() {
        List<String> list = new ArrayList<>();
        list.add("小说");
        list.add("动漫_Dm-123");
        list.add("电视剧");
        return list;
    }
}
