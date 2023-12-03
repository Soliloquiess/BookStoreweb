package com.bookstore.dao;

// 해시 생성 과정에서 발생할 수 있는 예외를 처리하기 위한 사용자 정의 예외 클래스입니다.
public class HashGenerationException extends RuntimeException {

    // 예외 메시지만을 포함하는 생성자입니다.
    public HashGenerationException(String message) {
        super(message);
    }

    // 예외 메시지와 해당 예외의 원인(cause)을 포함하는 생성자입니다.
    public HashGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

}
