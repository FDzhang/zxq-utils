package com.fd.demo.utils.secure;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/16 17:42
 */
@Slf4j
public class AesTest {


    @Test
    public void test1(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "测试_Test-123");
        jsonObject.put("like", mockList());
        jsonObject.put("emptyArray", new ArrayList<>());
        jsonObject.put("emptyStr", "");
        jsonObject.put("empty", null);

        String json = jsonObject.toJSONString();
        System.out.println(json);

        String hexAesKey = AesUtil.generateHexAesKey();
        System.out.println(hexAesKey);

        String iv = AesUtil.generateHexIv();
        System.out.println(iv);

        String encryptBase64 = AesUtil.encryptBase64(json, hexAesKey, iv);
        System.out.println(encryptBase64);

        String decryptStr = AesUtil.decryptStr(encryptBase64, hexAesKey, iv);
        System.out.println(decryptStr);
    }

    public static List<String> mockList() {
        List<String> list = new ArrayList<>();
        list.add("小说");
        list.add("动漫_Dm-123");
        list.add("电视剧");
        return list;
    }
}
