package com.fd.demo.utils.secure;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/17 14:04
 */

public class RsaUtil {
    /**
     * 生成用于非对称加密的RSA公钥和私钥，仅用于非对称加密<br>
     * 密钥对生成算法见：https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator
     * <p>
     * algorithm 非对称加密算法
     *
     * @return {@link KeyPair}
     */
    public static KeyPair generateKeyPair() {
        return SecureUtil.generateKeyPair("RSA");
    }

    /**
     * Returns a reference to the public key component of this key pair.
     *
     * @return a reference to the public key.
     */
    public static String getPublic(KeyPair keyPair) {
        PublicKey aPublic = keyPair.getPublic();
        byte[] encoded = aPublic.getEncoded();
        return Base64.encode(encoded);
    }

    /**
     * Returns a reference to the private key component of this key pair.
     *
     * @return a reference to the private key.
     */
    public static String getPrivate(KeyPair keyPair) {
        PrivateKey aPrivate = keyPair.getPrivate();
        byte[] encoded = aPrivate.getEncoded();
        return Base64.encode(encoded);
    }

    /**
     * 编码为Base64字符串，使用UTF-8编码
     *
     * @param data 被加密的字符串
     * @return Base64字符串
     */
    public static String encryptBase64(String data, String aPublic) {
        RSA rsa = new RSA(null, aPublic);
        return rsa.encryptBase64(data, KeyType.PublicKey);
    }

    /**
     * 解密为字符串，密文需为Hex（16进制）或Base64字符串
     *
     * @param data 数据，Hex（16进制）或Base64字符串
     * @return 解密后的密文
     */
    public static String decryptStr(String data, String aPrivate) {
        RSA rsa = new RSA(aPrivate, null);
        return rsa.decryptStr(data, KeyType.PrivateKey);
    }
}
