package com.fd.demo.utils.secure;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Aes
 *
 * @author ：zxq
 * @date ：Created in 2020/12/16 17:43
 */
public class AesUtil {

    /**
     * 生成一个随机的 128 位 AES秘钥 (length=32)
     */
    public static String generateHexAesKey() {
        byte[] encoded = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        return HexUtil.encodeHexStr(encoded);
    }

    /**
     * 生成一个随机的 128 位 AES秘钥
     */
    public static byte[] generateAesKey() {
        return SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
    }

    /**
     * 生成一个16进制的iv (length=16)
     */
    public static String generateHexIv() {
        List<Integer> list = Arrays.stream(RandomUtil.randomInts(16)).boxed().collect(Collectors.toList());
        return list.stream().map(HexUtil::toHex).collect(Collectors.joining());
    }

    /**
     * 加密，使用UTF-8编码
     *
     * @param data 被加密的字符串
     * @return 加密后的Base64
     */
    public static String encryptBase64(String data, String aesKey, String iv) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, aesKey.getBytes(CharsetUtil.CHARSET_UTF_8), iv.getBytes(CharsetUtil.CHARSET_UTF_8));
        return aes.encryptBase64(data);
    }

    /**
     * 解密Hex（16进制）或Base64表示的字符串，默认UTF-8编码
     *
     * @param data 被解密的String
     * @return 解密后的String
     */
    public static String decryptStr(String data, String aesKey, String iv) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, aesKey.getBytes(CharsetUtil.CHARSET_UTF_8), iv.getBytes(CharsetUtil.CHARSET_UTF_8));
        return aes.decryptStr(data);
    }
}
