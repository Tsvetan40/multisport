package com.demo.multisport.exceptions.plan;


public class DuplicatePlanException extends RuntimeException{
    public DuplicatePlanException() {
        super();
    }

    public DuplicatePlanException(String message) {
        super(message);
    }

    public DuplicatePlanException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatePlanException(Throwable cause) {
        super(cause);
    }
}
