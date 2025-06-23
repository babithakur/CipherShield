package com.example.ciphershield;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import android.util.Base64;

public class AESEncryption {

    private static SecretKeySpec getKeyFromMD5(String key) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5Hash = md.digest(key.getBytes("UTF-8"));
        return new SecretKeySpec(md5Hash, "AES");
    }

    public static String encrypt(String plainText, String key) throws Exception {
        SecretKeySpec secretKey = getKeyFromMD5(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        String encryptedBase64 = Base64.encodeToString(encrypted, Base64.DEFAULT);
        return encryptedBase64;
    }

    public static String decrypt(String encryptedText, String key) throws Exception {
        SecretKeySpec secretKey = getKeyFromMD5(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.decode(encryptedText, Base64.DEFAULT);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }

//    public static void main(String[] args) throws Exception {
//        String key = "mySecretKey";
//        String message = "Encrypt this message!";
//
//        String encrypted = encrypt(message, key);
//        String decrypted = decrypt(encrypted, key);
//
//        System.out.println("Encrypted: " + encrypted);
//        System.out.println("Decrypted: " + decrypted);
//    }
}

