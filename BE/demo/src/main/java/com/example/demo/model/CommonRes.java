package com.example.demo.model;

public class CommonRes {
    private String code;
    private String message;

    public CommonRes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonRes(String message) {
        this.message = message;
    }
}
