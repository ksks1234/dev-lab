package com.example.coupon.exception;

public class DuplicateIssueException extends RuntimeException {
    public DuplicateIssueException(String message) {
        super(message);
    }
}
