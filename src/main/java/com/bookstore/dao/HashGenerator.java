package com.bookstore.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private HashGenerator() {
        // 인스턴스화를 방지하기 위해 private 생성자를 선언합니다.
    }

    // 주어진 문자열을 MD5 해시로 변환하여 반환합니다.
    public static String generateMD5(String message) throws HashGenerationException {
        return hashString(message, "MD5");
    }

    // 주어진 메시지와 알고리즘으로 해시 문자열을 생성합니다.
    private static String hashString(String message, String algorithm)
            throws HashGenerationException {

        try {
            // 지정된 알고리즘의 해시 생성을 위해 MessageDigest 객체를 가져옵니다.
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            // UTF-8로 인코딩된 바이트 배열로부터 해시를 생성합니다.
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            // 바이트 배열을 16진수 문자열로 변환하여 반환합니다.
            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            // 알고리즘이나 인코딩 오류가 발생하면 HashGenerationException을 던집니다.
            throw new HashGenerationException(
                    "문자열로부터 해시를 생성할 수 없습니다", ex);
        }
    }

    // 바이트 배열을 16진수 문자열로 변환하는 메서드입니다.
    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < arrayBytes.length; i++) {
            // 각 바이트를 16진수로 변환하여 문자열에 추가합니다.
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}

//위 코드는 주어진 문자열을 MD5 해시로 변환하여 반환하며, 이를 위해 Java의 MessageDigest 클래스를 사용합니다. MessageDigest 클래스를 이용하여 주어진 알고리즘으로 해시를 생성하고, 해당 알고리즘에서 발생하는 예외를 처리하기 위해 NoSuchAlgorithmException과 UnsupportedEncodingException을 처리합니다. 16진수로 바이트 배열을 표현하기 위한 보조 메서드도 포함되어 있습니다.

