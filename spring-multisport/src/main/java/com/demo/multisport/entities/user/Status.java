package com.demo.multisport.entities.user;

public enum Status {
    BLOCKED("BLOCKED"),
    ACTIVE("ACTIVE");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
