package com.example.demoLogin.controller;

import com.example.demoLogin.entity.LoanApplication;
import com.example.demoLogin.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoanApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoanRepository loanApplicationRepository;

    // Use BeforeEach to ensure a clean state before each test
    @BeforeEach
    void setUp() {
        loanApplicationRepository.deleteAll(); // Clear the database
    }

    @Test
    void whenGetApplicationsByCompanyName_thenReturnCorrectJsonArray() throws Exception {
        // Given
        LoanApplication app1 = new LoanApplication();
        app1.setCompanyName("Alpha Corp");
        app1.setLoanType("Business");
        app1.setLoanAmount(200000.0);
        loanApplicationRepository.save(app1);

        LoanApplication app2 = new LoanApplication();
        app2.setCompanyName("Alpha Corp");
        app2.setLoanType("SME");
        app2.setLoanAmount(50000.0);
        loanApplicationRepository.save(app2);

        // When & Then
        mockMvc.perform(get("/applications/company/Alpha Corp")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName").value("Alpha Corp"))
                .andExpect(jsonPath("$[1].loanType").value("SME"));
    }

    @Test
    void whenGetApplicationsByNonExistentCompany_thenReturnEmptyArray() throws Exception {
        // Given: No data for "Non-existent Company"

        // When & Then
        mockMvc.perform(get("/applications/company/Non-existent Company")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}