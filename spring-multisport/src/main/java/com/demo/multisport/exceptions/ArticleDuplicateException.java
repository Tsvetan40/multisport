package com.demo.multisport.exceptions;

public class ArticleDuplicateException extends RuntimeException {
    public ArticleDuplicateException() {
    }

    public ArticleDuplicateException(String message) {
        super(message);
    }

    public ArticleDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleDuplicateException(Throwable cause) {
        super(cause);
    }
}
