package com.emarsys.issutracker.duedatecalculator.controller;

import com.emarsys.issutracker.duedatecalculator.model.ReportedIssue;
import com.emarsys.issutracker.duedatecalculator.service.DueDateCalculatorService;
import com.emarsys.issutracker.duedatecalculator.service.IssueService;
import com.emarsys.issutracker.duedatecalculator.service.OutOfTimeRangeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class IssueTrackerController {

    private final DueDateCalculatorService dueDateCalculatorService;
    private final IssueService issueService;

    @Value("${issue.tracker.turnaround.time:1}")
    private int turnaroundTime;

    @PostMapping("/issue")
    public ReportedIssue submitIssue() {
        LocalDateTime submitDateTime = LocalDateTime.now();
        try {
            LocalDateTime calculatedDateTime = dueDateCalculatorService.calculateDueDate(submitDateTime, turnaroundTime);
            return issueService.storeCreatedIssue(submitDateTime, calculatedDateTime, turnaroundTime);
        } catch (OutOfTimeRangeException e) {
            log.warn("Issue is submitted on non-working hour/weekend, please report issues during working hours!", e);
            return null;
        }
    }

    @GetMapping("/all_issues")
    public ConcurrentMap<UUID, ReportedIssue> getAllIssues() {
        return issueService.getReportedIssues();
    }

    @GetMapping("/all_issues/{id}")
    public String getCalculatedResolveTime(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return issueService.getCalculatedResolveTimeOfIssue(uuid);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID String: {}, try to provide a valid one.", id, e);
            return id + " is an invalid UUID.";
        }
    }
}
