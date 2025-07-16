package com.postgresql.demo1.controller;

public class UnderAgeException extends RuntimeException {
    public UnderAgeException(String message) {
        super(message);
    }
}