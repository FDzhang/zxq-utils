package com.fd.demo.utils.secure;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSONObject;
import com.fd.demo.utils.mock.JsonMock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/16 17:42
 */
@Slf4j
public class DigestTest {


    @Test
    public void test1() {
        JSONObject jsonObject = JsonMock.mockJsonObject();
        String json = jsonObject.toJSONString();
        log.info("json: {}", json);

        String md5 = MyDigestUtil.md5Hex(json);
        log.info("md5: {}", md5);

        String sha1 = MyDigestUtil.sha1Hex(json);
        log.info("sha1: {}", sha1);

        String sha256 = MyDigestUtil.sha256Hex(json);
        log.info("sha256: {}", sha256);
    }

    @Test
    public void t() throws UnsupportedEncodingException {
//        JSONObject jsonObject = JsonMock.mockJsonObject();
//        String json = jsonObject.toJSONString();
//        log.info("json: {}", json);
//        String encode = URLEncoder.encode(json, "utf-8");
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String str = "{\"emptyStr\":\"\",\"like\":[\"小说\",\"动漫_Dm-123\",\"电视剧\"],\"name\":\"测试_Test-123\",\"birth\":1608198537004,\"emptyArray\":[]}";
        System.out.println(str);

        // 5393554e94bf0eb6436f240a4fd71282
        String digestHex = md5.digestHex(str);
        System.out.println(digestHex);
    }

}
