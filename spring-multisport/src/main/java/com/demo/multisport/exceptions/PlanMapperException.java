package com.demo.multisport.exceptions;

public class PlanMapperException extends RuntimeException{
    public PlanMapperException() {
    }

    public PlanMapperException(String message) {
        super(message);
    }

    public PlanMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlanMapperException(Throwable cause) {
        super(cause);
    }
}
