package com.demo.multisport.exceptions.user;

public class UserDuplicateException extends RuntimeException{
    public UserDuplicateException() {
    }

    public UserDuplicateException(String message) {
        super(message);
    }

    public UserDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDuplicateException(Throwable cause) {
        super(cause);
    }
}
