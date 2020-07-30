package com.emarsys.issutracker.duedatecalculator.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DueDateCalculatorService {

    private static final List<DayOfWeek> daysOfWeekend = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    public LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnaroundTime) throws OutOfTimeRangeException {
        if (daysOfWeekend.contains(submitDateTime.getDayOfWeek()) || !isSubmitTimeWorkingHour(submitDateTime)) {
            throw new OutOfTimeRangeException("Submit date/time is not working day/hour");
        }
        return null;
    }

    private boolean isSubmitTimeWorkingHour(LocalDateTime submitDateTime) {
        return submitDateTime.getHour() <= 17 || submitDateTime.getHour() >= 9;
    }
}
