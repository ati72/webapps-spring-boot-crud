package com.d2ovj9.exam.exception;

public class PlayerApiRequestException extends RuntimeException{
    public PlayerApiRequestException(String message) {
        super(message);
    }

    public PlayerApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }


}
