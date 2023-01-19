package com.demo.multisport.exceptions.article;

public class NoSuchArticleException extends RuntimeException{
    public NoSuchArticleException() {
        super();
    }

    public NoSuchArticleException(String message) {
        super(message);
    }

    public NoSuchArticleException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchArticleException(Throwable cause) {
        super(cause);
    }
}
