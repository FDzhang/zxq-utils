package com.fd.demo.timeconsume.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/26 9:15
 */
@Data
public class UserIdCardInfo implements Serializable {

    private static final long serialVersionUID = 3661648649528790984L;

    private String gender;
    private Integer age;
    /**
     * 生日
     */
    private String birth;
    /**
     * 星座
     */
    private String zodiac;
    /**
     * 属相
     */
    private String chineseZodiac;
    /**
     * 省份
     */
    private String province;
}
