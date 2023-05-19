package com.library.pro.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: EmailMatcher <br/>
 * @description: 邮箱匹配器 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public class EmailMatcher {

    /**
     * 邮箱格式正则表达式
     */
    private static final String EMAIL_PATTERN = "^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,})$";

    /**
     * 判断邮箱地址是否符合格式
     * @param email 邮箱地址
     * @return true 表示符合格式，false 表示不符合格式
     */
    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
