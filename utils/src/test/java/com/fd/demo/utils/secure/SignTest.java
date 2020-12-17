package com.fd.demo.utils.secure;

import com.alibaba.fastjson.JSONObject;
import com.fd.demo.utils.mock.JsonMock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/16 17:42
 */
@Slf4j
public class SignTest {


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

        //签名
        String signed = SignUtil.signHex(data, aPrivate);
        log.info("signed: {}", signed);

        //验证签名
        boolean verify = SignUtil.verify(data, signed, aPublic);
        log.info("verify: {}", verify);

        Assert.assertEquals(verify, true);
    }

}
