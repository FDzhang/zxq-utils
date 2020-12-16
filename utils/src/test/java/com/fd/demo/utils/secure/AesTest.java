package com.fd.demo.utils.secure;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/16 17:42
 */

public class AesTest {


    @Test
    public void test1(){

        byte[] aesKey = AesUtil.getAesKey();



        byte[] encoded = SecureUtil.aes().getSecretKey().getEncoded();

        for (byte b : encoded) {
            System.out.println(Integer.toHexString(b));
        }

        String hex = HexUtil.encodeHexStr(encoded);
        System.out.println(hex);
    }
}
