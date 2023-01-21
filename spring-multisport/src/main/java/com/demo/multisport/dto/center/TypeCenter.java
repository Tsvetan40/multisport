package com.demo.multisport.dto.center;

public enum TypeCenter {
    SPORT_CENTER("SportCenter"),
    RELAX_CENTER("RelaxCenter");

    private String center;

    TypeCenter(String center) {
        this.center = center;
    }

    public String getCenter() {
        return this.center;
    }
}
