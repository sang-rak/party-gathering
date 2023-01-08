package com.jackdang.common.exception;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(){
        super("인증되지 않은 접근입니다.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}