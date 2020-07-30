package com.emarsys.issutracker.duedatecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ReportedIssue {

    private UUID uuid;
    private LocalDateTime submitDateTime;
    private LocalDateTime calculatedResolveTime;
    private int turnaroundTime;
}
