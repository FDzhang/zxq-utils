package com.fd.demo.utils.utils;

import org.slf4j.MDC;

import java.util.Locale;

public class UserUtil {

    private final static ThreadLocal<String> tlUser = new ThreadLocal<String>();

    private final static ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>() {
        @Override
        protected Locale initialValue() {
            // 语言的默认值
            return Locale.CHINESE;
        }

        ;
    };

    public static final String KEY_LANG = "lang";

    public static final String KEY_USER = "user";

    public static void setUser(String userId) {
        tlUser.set(userId);

        // 把用户信息放到log4j
        MDC.put(KEY_USER, userId);
    }

    /**
     * 如果没有登录，返回null
     *
     * @return
     */
    public static String getUserIfLogin() {
        return tlUser.get();
    }

    /**
     * 如果没有登录会抛出异常
     *
     * @return
     */
    public static String getUser() {
        String user = tlUser.get();

        if (user == null) {
//            throw new UnloginException();
            throw new RuntimeException();
        }

        return user;
    }

    public static void setLocale(String locale) {
        setLocale(new Locale(locale));
    }

    public static void setLocale(Locale locale) {
        tlLocale.set(locale);
    }

    public static Locale getLocale() {
        return tlLocale.get();
    }

    public static void clearAllUserInfo() {
        tlUser.remove();
        tlLocale.remove();

        MDC.remove(KEY_USER);
    }

}
