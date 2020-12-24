package com.fd.demo.utils.secure;

import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/24 14:31
 */

public class JwtUtil {

    /**
     * 过期时间（毫秒单位）5 分钟
     */
    private final static long TOKEN_EXPIRE_5 = 1000 * 60 * 5;

    public static String createToken(Map<String, Object> claimMap, Key key) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis+TOKEN_EXPIRE_5))
                .addClaims(claimMap)
                .signWith(key)
                .compact();
    }

    public static boolean verifyToken(String token, Key key) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Object> parseToken(String token, Key key) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        // 生成密钥
        String key = AesUtil.generateHexAesKey();
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    }
}
