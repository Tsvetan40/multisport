package com.demo.multisport.exceptions;

public class CenterDuplicateException extends RuntimeException {
    public CenterDuplicateException() {
    }

    public CenterDuplicateException(String message) {
        super(message);
    }

    public CenterDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CenterDuplicateException(Throwable cause) {
        super(cause);
    }
}
