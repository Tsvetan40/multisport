package com.demo.multisport.exceptions;

public class CenterNotFoundException extends RuntimeException {
    public CenterNotFoundException() {
        super();
    }

    public CenterNotFoundException(String message) {
        super(message);
    }

    public CenterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CenterNotFoundException(Throwable cause) {
        super(cause);
    }
}
