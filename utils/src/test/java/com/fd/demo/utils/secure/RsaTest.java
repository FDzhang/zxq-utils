package com.fd.demo.utils.secure;

import com.alibaba.fastjson.JSONObject;
import com.fd.demo.utils.mock.JsonMock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/16 17:42
 */
@Slf4j
public class RsaTest {


    @Test
    public void test1() {
        JSONObject jsonObject = JsonMock.mockJsonObject();

        String data = jsonObject.toJSONString();
        log.info("data: {}", data);

        KeyPair keyPair = RsaUtil.generateKeyPair();

        String aPublic = RsaUtil.getPublic(keyPair);
        log.info("aPublic: {}", aPublic);
        String aPrivate = RsaUtil.getPrivate(keyPair);
        log.info("aPrivate: {}", aPrivate);

        String encryptBase64 = RsaUtil.encryptBase64(data, aPublic);
        log.info("encryptBase64: {}", encryptBase64);

        String decryptStr = RsaUtil.decryptStr(encryptBase64, aPrivate);
        log.info("decryptStr: {}", decryptStr);

        Assert.assertEquals(data, decryptStr);
    }


}
