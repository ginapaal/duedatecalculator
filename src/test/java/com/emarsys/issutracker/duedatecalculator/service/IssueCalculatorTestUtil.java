package com.emarsys.issutracker.duedatecalculator.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class IssueCalculatorTestUtil {

    public static LocalDateTime getLocalDateTime(int month, int dayOfMonth, int hours) {
        return LocalDateTime.of(LocalDate.of(2020, month, dayOfMonth),
                LocalTime.of(hours, 2, 0));
    }
}
