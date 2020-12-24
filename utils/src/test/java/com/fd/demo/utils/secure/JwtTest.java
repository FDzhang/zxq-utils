package com.fd.demo.utils.secure;

import cn.hutool.core.util.HexUtil;
import com.alibaba.fastjson.JSONObject;
import com.fd.demo.utils.mock.JsonMock;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/24 14:39
 */
@Slf4j
public class JwtTest {

    @Test
    public void test() {
        // 生成密钥
        String key = "0123456789_0123456789_0123456789";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
        PrivateKey aPrivate = keyPair.getPrivate();
        // 1. 生成 token
        String token = Jwts.builder()   // 创建 JWT 对象
                .setSubject("Json Web Token")   // 设置主题（声明信息）
                .signWith(aPrivate)  // 设置安全密钥（生成签名所需的密钥和算法）
                .compact();  // 生成token（1.编码 Header 和 Payload 2.生成签名 3.拼接字符串）
        log.info(token);

        // 2. 验证token，如果验证token失败则会抛出异常
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            log.info("验证成功");
        } catch (JwtException e) {
            log.error("验证失败");
            log.error(e.getMessage());
        }
        // 3. 解析token
        Claims body = Jwts.parserBuilder()  // 创建解析对象
                .setSigningKey(secretKey)  // 设置安全密钥（生成签名所需的密钥和算法）
                .build()
                .parseClaimsJws(token)  // 解析token
                .getBody();        // 获取 payload 部分内容
        log.info("body: {}", body);
    }


    @Test
    public void test1() {

        JSONObject jsonObject = JsonMock.mockJsonObject();
        Map<String, Object> innerMap = jsonObject.getInnerMap();

        String aesKey = AesUtil.generateHexAesKey();



    }
}
