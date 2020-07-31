package com.emarsys.issutracker.duedatecalculator.service;

import com.emarsys.issutracker.duedatecalculator.model.ReportedIssue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Slf4j
public class IssueService {

    @Getter
    private final ConcurrentMap<UUID, ReportedIssue> reportedIssues = new ConcurrentHashMap<>();

    public ReportedIssue storeCreatedIssue(LocalDateTime submitDateTime, LocalDateTime calculatedDateTime, int turnaroundTime) {
        ReportedIssue reportedIssue = ReportedIssue.builder()
                .uuid(UUID.randomUUID())
                .submitDateTime(submitDateTime)
                .calculatedResolveTime(getCalculatedTimeString(calculatedDateTime))
                .turnaroundTime(turnaroundTime)
                .build();
        reportedIssues.putIfAbsent(reportedIssue.getUuid(), reportedIssue);
        log.info("Issue is reported and saved with UUID: {}", reportedIssue.getUuid());
        return reportedIssue;
    }

    private String getCalculatedTimeString(LocalDateTime calculatedDateTime) {
        return calculatedDateTime.toLocalTime()+
                ", " +
                calculatedDateTime.getDayOfWeek() +
                ", " +
                calculatedDateTime.toLocalDate();
    }

    public String getCalculatedResolveTimeOfIssue(UUID uuid) {
        String calculatedResolveTime = reportedIssues.getOrDefault(uuid, new ReportedIssue()).getCalculatedResolveTime();
        return calculatedResolveTime != null ? calculatedResolveTime : "No issue is reported with this id: " + uuid;
    }
}
