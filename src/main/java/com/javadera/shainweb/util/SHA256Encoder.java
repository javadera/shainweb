package com.javadera.shainweb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class SHA256Encoder {

    /**
     * SHA-256 でハッシュ値を計算後、Base64 でエンコードした文字列を取得します。
     * @param origPassword
     * @return
     */
    public static String getBase64EncodedPassword(String origPassword) {
        String returnValue = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(origPassword.getBytes());
            returnValue = Base64.encodeBase64String(digest).replaceAll("\r\n", "");
        } catch (NoSuchAlgorithmException e) {
        }
        return returnValue;
    }

}
