package com.emarsys.issutracker.duedatecalculator.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DueDateCalculatorService {

    private static final int FIVE_PM_INT = 17;
    private static final int NINE_AM_INT = 9;
    private static final List<DayOfWeek> daysOfWeekend = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    public LocalDateTime calculateDueDate(LocalDateTime submitDateTime, int turnaroundTime) throws OutOfTimeRangeException {
        if (daysOfWeekend.contains(submitDateTime.getDayOfWeek()) || !isSubmitTimeWorkingHour(submitDateTime)) {
            throw new OutOfTimeRangeException("Submit date/time is not working day/hour");
        }
        return getIssueResolveDateTime(submitDateTime, turnaroundTime);
    }

    private LocalDateTime getIssueResolveDateTime(LocalDateTime submitDateTime, int turnaroundTime) {
        int i = 0;
        while (i < turnaroundTime) {
            if (submitDateTime.getHour() == FIVE_PM_INT) {
                submitDateTime = LocalDateTime.of(submitDateTime.toLocalDate().plusDays(1), LocalTime.of(NINE_AM_INT, submitDateTime.getMinute()));
            }
            if (submitDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                submitDateTime = LocalDateTime.of(submitDateTime.toLocalDate().plusDays(2), LocalTime.of(NINE_AM_INT, submitDateTime.getMinute()));
            }
            submitDateTime = submitDateTime.plusHours(1);
            i++;
        }
        return submitDateTime;
    }

    private boolean isSubmitTimeWorkingHour(LocalDateTime submitDateTime) {
        return submitDateTime.getHour() < FIVE_PM_INT && submitDateTime.getHour() >= NINE_AM_INT;
    }
}
