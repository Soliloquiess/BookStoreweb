package com.bookstore.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashGeneratorTest {

    @Test
    public void testGenerateMD5() {
        // 테스트할 비밀번호 문자열입니다.
        String password = "mysecret";
        // 주어진 비밀번호를 MD5로 암호화하여 encryptedPassword에 저장합니다.
        String encryptedPassword = HashGenerator.generateMD5(password);
        
        // 암호화된 비밀번호를 콘솔에 출력합니다.
        System.out.println(encryptedPassword);
        
        // 단순한 assertTrue(true)로 테스트가 항상 통과되도록 설정되어 있습니다.
        // 실제로는 예상된 해시 값을 비교하는 등의 더 많은 테스트가 필요합니다.
        assertTrue(true);
    }

}
