package com.emarsys.issutracker.duedatecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportedIssue {

    private UUID uuid;
    private LocalDateTime submitDateTime;
    private String calculatedResolveTime;
    private int turnaroundTime;
}
