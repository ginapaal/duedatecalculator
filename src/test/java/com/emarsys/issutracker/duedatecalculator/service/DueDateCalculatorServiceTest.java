package com.emarsys.issutracker.duedatecalculator.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DueDateCalculatorServiceTest {

    private final DueDateCalculatorService dueDateCalculatorService = new DueDateCalculatorService();

    @Test
    void testIfDateTimeCalculatedAsExpected() throws OutOfTimeRangeException {
        int turnaroundTime = 10;
        LocalDateTime calculatedTime = dueDateCalculatorService.calculateDueDate(getLocalDateTime(7, 28, 10), turnaroundTime);
        assertEquals(getLocalDateTime(7, 29, 12), calculatedTime);
    }

    @Test
    void testIfDateTimeCalculatedAsExpectedOnSameDay() throws OutOfTimeRangeException {
        int turnaroundTime = 3;
        LocalDateTime calculatedTime = dueDateCalculatorService.calculateDueDate(getLocalDateTime(7, 28, 10), turnaroundTime);
        assertEquals(getLocalDateTime(7, 28, 13), calculatedTime);
    }

    @Test
    void testIfDateTimeCalculatedAsExpectedWithWeekend() throws OutOfTimeRangeException {
        int turnaroundTime = 16;
        LocalDateTime calculatedTime = dueDateCalculatorService.calculateDueDate(getLocalDateTime(7, 30, 15), turnaroundTime);
        assertEquals(getLocalDateTime(8, 3, 15), calculatedTime);
    }

    @Test
    void testIfExceptionIsThrownWhenSubmitTimeIsWeekend() throws OutOfTimeRangeException {
        assertThrows(OutOfTimeRangeException.class, () -> {
            dueDateCalculatorService.calculateDueDate(getLocalDateTime(7, 25, 14), 16);
        });
    }


    @Test
    void testIfExceptionIsThrownWhenSubmitTimeIsAfter5PM() throws OutOfTimeRangeException {
        assertThrows(OutOfTimeRangeException.class, () -> {
            dueDateCalculatorService.calculateDueDate(getLocalDateTime(7, 30, 17), 16);
        });
    }

    private LocalDateTime getLocalDateTime(int month, int dayOfMonth, int hours) {
        return LocalDateTime.of(LocalDate.of(2020, month, dayOfMonth),
                LocalTime.of(hours, 2, 0));
    }
}