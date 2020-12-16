package com.fd.demo.utils.secure;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/16 17:43
 */
public class AesUtil {

    /** 默认的AES加密方式：AES/CBC/PKCS5Padding */
    public static String getHexAesKey(){
        byte[] encoded = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        return HexUtil.encodeHexStr(encoded);
    }

    /** 默认的AES加密方式：AES/CBC/PKCS5Padding */
    public static byte[] getAesKey(){
        return SecureUtil.aes().getSecretKey().getEncoded();
    }

    /** 默认的AES加密方式：AES/CBC/PKCS5Padding */
    public static byte[] getAesKey(String iv){
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, "0CoJUm6Qyw8W8jud".getBytes(), "0102030405060708".getBytes());
        return SecureUtil.aes().getSecretKey().getEncoded();
    }

    public static void main(String[] args) {
        byte[] bytes = "0CoJUm6Qyw8W8jud".getBytes();
        System.out.println(bytes.length);
        for (byte aByte : bytes) {

            System.out.println(aByte);
        }
    }
}
