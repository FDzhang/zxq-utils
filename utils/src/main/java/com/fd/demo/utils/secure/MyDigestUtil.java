package com.fd.demo.utils.secure;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/17 16:44
 */

public class MyDigestUtil {

    /**
     * 计算32位MD5摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return MD5摘要的16进制表示
     */
    public static String md5Hex(String data, String salt) {
        MD5 md5 = new MD5(salt.getBytes(CharsetUtil.CHARSET_UTF_8));
        return md5.digestHex(data);
    }

    public static String md5Hex(String data) {
        return DigestUtil.md5Hex(data);
    }

    /**
     * 计算SHA-1摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return SHA-1摘要的16进制表示
     */
    public static String sha1Hex(String data) {
        return DigestUtil.sha1Hex(data);
    }

    /**
     * 计算SHA-256摘要值，并转为16进制字符串
     *
     * @param data 被摘要数据
     * @return SHA-256摘要的16进制表示
     */
    public static String sha256Hex(String data) {
        return DigestUtil.sha256Hex(data);
    }


}
