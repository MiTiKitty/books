package com.library.pro.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @className: PasswordHelper <br/>
 * @description: 密码工具类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public class PasswordHelper {

    private static final String ALGORITHM = "SHA-256";

    /**
     * 生成加密密码
     *
     * @param password
     *         原始密码
     * @return 加密密码
     */
    public static String generateEncryptedPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] digest = messageDigest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查密码是否匹配
     *
     * @param password
     *         原始密码
     * @param encryptedPassword
     *         加密密码
     * @return 是否匹配
     */
    public static boolean checkPassword(String password, String encryptedPassword) {
        return generateEncryptedPassword(password).equals(encryptedPassword);
    }

}
