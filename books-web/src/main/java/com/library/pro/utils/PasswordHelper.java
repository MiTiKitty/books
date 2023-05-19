package com.library.pro.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @className: PasswordHelper <br/>
 * @description: 密码工具类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/19 <br/>
 * @version: 1.0.0 <br/>
 */
public class PasswordHelper {

    /**
     * 盐的长度
     */
    private static final int SALT_LENGTH = 16;

    /**
     * 哈希迭代次数
     */
    private static final int HASH_ITERATIONS = 1000;

    /**
     * 哈希算法
     */
    private static final String HASH_ALGORITHM = "SHA-256";

    /**
     * 生成随机盐
     */
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 计算密码哈希值
     *
     * @param password
     *         原始密码
     * @param salt
     *         盐
     * @return 哈希值
     */
    private static byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.reset();
            byte[] passwordBytes = password.getBytes();
            byte[] saltedPasswordBytes = new byte[passwordBytes.length + salt.length];
            System.arraycopy(passwordBytes, 0, saltedPasswordBytes, 0, passwordBytes.length);
            System.arraycopy(salt, 0, saltedPasswordBytes, passwordBytes.length, salt.length);
            digest.update(saltedPasswordBytes);
            byte[] hash = digest.digest();
            for (int i = 0; i < HASH_ITERATIONS; i++) {
                digest.reset();
                hash = digest.digest(hash);
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证密码是否正确
     *
     * @param password
     *         原始密码
     * @param storedHashedPassword
     *         存储的哈希密码
     * @return true 表示密码正确，false 表示密码错误
     */
    private static boolean checkPassword(String password, byte[] storedHashedPassword) {
        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(storedHashedPassword, 0, salt, 0, SALT_LENGTH);
        byte[] hash = new byte[storedHashedPassword.length - SALT_LENGTH];
        System.arraycopy(storedHashedPassword, SALT_LENGTH, hash, 0, hash.length);
        byte[] newHash = hashPassword(password, salt);
        return MessageDigest.isEqual(newHash, hash);
    }

    /**
     * 根据密码生成加密密码
     *
     * @return 密码
     */
    public static String generatePassword(String password) {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(password, salt);
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    /**
     * 验证密码是否正确
     *
     * @param password
     *         原始密码
     * @param encodedHashedPassword
     *         存储的hash密码
     * @return true 表示密码正确，false 表示密码错误
     */
    public static boolean checkPassword(String password, String encodedHashedPassword) {
        return checkPassword(password, Base64.getDecoder().decode(encodedHashedPassword));
    }

}
