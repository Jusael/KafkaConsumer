package com.example.kafkaconsumer.exception;

public class BusinessException extends RuntimeException {
    private final String errorCode;

    public BusinessException(String message, String errorCode) {
        super(message); // RuntimeException의 message 필드 설정
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}