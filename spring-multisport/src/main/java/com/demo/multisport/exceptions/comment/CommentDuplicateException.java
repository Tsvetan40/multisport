package com.demo.multisport.exceptions.comment;

public class CommentDuplicateException extends RuntimeException{
    public CommentDuplicateException() {
        super();
    }

    public CommentDuplicateException(String message) {
        super(message);
    }

    public CommentDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentDuplicateException(Throwable cause) {
        super(cause);
    }
}
