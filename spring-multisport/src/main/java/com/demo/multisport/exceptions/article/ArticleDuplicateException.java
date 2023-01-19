package com.demo.multisport.exceptions.article;

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
