package com.taskmanager.user.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final String code;
    private final String message;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.message = msg;
    }
}
