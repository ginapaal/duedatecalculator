package com.emarsys.issutracker.duedatecalculator.service;

import com.emarsys.issutracker.duedatecalculator.model.ReportedIssue;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import static com.emarsys.issutracker.duedatecalculator.service.IssueCalculatorTestUtil.getLocalDateTime;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IssueServiceTest {

    private final IssueService issueService = new IssueService();

    @Test
    public void testIfIssueCreatedProperly() {
        ReportedIssue reportedIssue = issueService.storeCreatedIssue(getLocalDateTime(7, 30, 11), getLocalDateTime(7, 30, 15), 4);
        assertTrue(new ReflectionEquals(getExpectedReportedIssue(), "uuid").matches(reportedIssue));
    }

    private ReportedIssue getExpectedReportedIssue() {
        return ReportedIssue.builder()
                .submitDateTime(getLocalDateTime(7, 30, 11))
                .turnaroundTime(4)
                .calculatedResolveTime("15:02, THURSDAY, 2020-07-30")
                .build();
    }

}