package com.emarsys.issutracker.duedatecalculator.service;

public class OutOfTimeRangeException extends Throwable {

    public OutOfTimeRangeException(String message) {
        super(message);
    }
}
