package com.fd.demo.utils.secure;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

/**
 * 签名
 *
 * @author ：zxq
 * @date ：Created in 2020/12/17 16:06
 */

public class SignUtil {
    /**
     * 用私钥对信息生成数字签名
     *
     * @param data 加密数据
     * @return 签名
     */
    public static String signHex(String data, String privateKeyStr) {
        Sign sign = new Sign(SignAlgorithm.MD5withRSA, privateKeyStr, null);
        byte[] signed = sign.sign(data.getBytes(CharsetUtil.CHARSET_UTF_8));
        return HexUtil.encodeHexStr(signed);
    }

    /**
     * 用公钥检验数字签名的合法性
     *
     * @param data   数据
     * @param signed 签名
     * @return 是否验证通过
     */
    public static boolean verify(String data, String signed, String publicKeyStr) {
        Sign sign = new Sign(SignAlgorithm.MD5withRSA, null, publicKeyStr);
        return sign.verify(data.getBytes(CharsetUtil.CHARSET_UTF_8), HexUtil.decodeHex(signed));
    }
}
