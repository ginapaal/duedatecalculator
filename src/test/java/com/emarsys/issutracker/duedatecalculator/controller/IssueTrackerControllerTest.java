package com.emarsys.issutracker.duedatecalculator.controller;

import com.emarsys.issutracker.duedatecalculator.service.DueDateCalculatorService;
import com.emarsys.issutracker.duedatecalculator.service.IssueService;
import com.emarsys.issutracker.duedatecalculator.service.OutOfTimeRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class IssueTrackerControllerTest {

    private MockMvc mockMvc;
    @Mock
    private DueDateCalculatorService dueDateCalculatorService;
    @Mock
    private IssueService issueService;
    @InjectMocks
    private IssueTrackerController issueTrackerController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(issueTrackerController)
                .build();
    }

    @Test
    void testEndPoint() throws Exception, OutOfTimeRangeException {
        ReflectionTestUtils.setField(issueTrackerController, "turnaroundTime", 16);
        this.mockMvc.perform(post("/issue")).andExpect(status().isOk());
        verify(dueDateCalculatorService).calculateDueDate(any(), eq(16));
    }
}