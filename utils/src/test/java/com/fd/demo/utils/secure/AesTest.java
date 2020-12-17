package com.fd.demo.utils.secure;

import com.alibaba.fastjson.JSONObject;
import com.fd.demo.utils.mock.JsonMock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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
    public void test1() {
        JSONObject jsonObject = JsonMock.mockJsonObject();

        String json = jsonObject.toJSONString();
        log.info("data: {}", json);

        String hexAesKey = AesUtil.generateHexAesKey();
        log.info("hexAesKey: {}", hexAesKey);

        String iv = AesUtil.generateHexIv();
        log.info("iv: {}", iv);

        String encryptBase64 = AesUtil.encryptBase64(json, hexAesKey, iv);
        log.info("encryptBase64: {}", encryptBase64);

        String decryptStr = AesUtil.decryptStr(encryptBase64, hexAesKey, iv);
        log.info("decryptStr: {}", decryptStr);

        Assert.assertEquals(json, decryptStr);
    }
}
