package com.fd.demo.utils.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSONObject;
import com.fd.demo.utils.mock.JsonMock;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/23 17:51
 */

public class Base64Util {

    /**
     * Base64 有三个字符+、/和=，在 URL 里面有特殊含义，所以要被替换掉：=被省略、+替换成-，/替换成_ 。这就是 Base64URL 算法。
     */
    public static String encodeUrlSafe(String str){
        return Base64.encodeUrlSafe(str, CharsetUtil.CHARSET_UTF_8);
    }

    public static String encode(String str){
        return Base64.encode(str, CharsetUtil.CHARSET_UTF_8);
    }

    public static void main(String[] args) {
        JSONObject jsonObject = JsonMock.mockJsonObject();
        String jsonString = jsonObject.toJSONString();

        String encode = encode(jsonString);
        String encodeUrlSafe = encodeUrlSafe(jsonString);
        System.out.println(encode);
        System.out.println(encodeUrlSafe);
        System.out.println(encode.equals(encodeUrlSafe));
    }
}
