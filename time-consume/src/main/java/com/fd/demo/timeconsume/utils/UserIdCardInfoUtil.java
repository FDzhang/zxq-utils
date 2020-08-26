package com.fd.demo.timeconsume.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import com.fd.demo.timeconsume.bean.UserIdCardInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/26 9:15
 */
@Slf4j
public class UserIdCardInfoUtil {
    public static UserIdCardInfo getUserInfoByIdCard(String idCard) {
        if (!IdcardUtil.isValidCard(idCard)) {
            log.error("无效idCard");
            return null;
        }
        return getUserIdCardInfo(idCard);
    }

    private static UserIdCardInfo getUserIdCardInfo(String idCard) {
        UserIdCardInfo userIdCardInfo = new UserIdCardInfo();

        int age = IdcardUtil.getAgeByIdCard(idCard);
        String gender = IdcardUtil.getGenderByIdCard(idCard) == 1 ? "男" : "女";
        String birth = IdcardUtil.getBirth(idCard);
        String province = IdcardUtil.getProvinceByIdCard(idCard);
        DateTime date = DateUtil.parse(birth);
        int year = date.year();
        int month = date.month();
        int day = date.dayOfMonth();

        userIdCardInfo.setAge(age);
        userIdCardInfo.setGender(gender);
        userIdCardInfo.setBirth(birth);
        userIdCardInfo.setProvince(province);
        userIdCardInfo.setZodiac(DateUtil.getZodiac(month, day));
        userIdCardInfo.setChineseZodiac(DateUtil.getChineseZodiac(year));
        return userIdCardInfo;
    }
}
