package com.emarsys.issutracker.duedatecalculator.service;

import com.emarsys.issutracker.duedatecalculator.model.ReportedIssue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Slf4j
public class IssueService {

    private static ConcurrentMap<UUID, ReportedIssue> reportedIssues = new ConcurrentHashMap<>();

    public void storeCreatedIssue(LocalDateTime submitDateTime, LocalDateTime calculatedDateTime, int turnaroundTime) {
        ReportedIssue reportedIssue = ReportedIssue.builder()
                .uuid(UUID.randomUUID())
                .submitDateTime(submitDateTime)
                .calculatedResolveTime(calculatedDateTime)
                .turnaroundTime(turnaroundTime)
                .build();
        reportedIssues.putIfAbsent(reportedIssue.getUuid(), reportedIssue);
        log.info("Issue is reported and saved with UUID: {}", reportedIssue.getUuid());
        System.out.println(reportedIssues.toString());
    }
}
