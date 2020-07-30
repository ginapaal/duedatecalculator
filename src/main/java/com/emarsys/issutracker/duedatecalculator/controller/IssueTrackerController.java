package com.emarsys.issutracker.duedatecalculator.controller;

import com.emarsys.issutracker.duedatecalculator.service.DueDateCalculatorService;
import com.emarsys.issutracker.duedatecalculator.service.OutOfTimeRangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IssueTrackerController {

    private final DueDateCalculatorService dueDateCalculatorService;

    @Value("${issue.tracker.turnaround.time:1}")
    private int turnaroundTime;

    @PostMapping("/issue")
    public void submitIssue() {
        LocalDateTime submitDateTime = LocalDateTime.now();
        try {
            dueDateCalculatorService.calculateDueDate(submitDateTime, turnaroundTime);
        } catch (OutOfTimeRangeException e) {

        }
    }
}
