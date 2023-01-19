package com.demo.multisport.exceptions.plan;

public class NoSuchPlanException extends RuntimeException{
    public NoSuchPlanException() {
        super();
    }

    public NoSuchPlanException(String message) {
        super(message);
    }

    public NoSuchPlanException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPlanException(Throwable cause) {
        super(cause);
    }
}
