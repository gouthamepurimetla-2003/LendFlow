package com.example.demoLogin.repository;

import com.example.demoLogin.entity.LoanApplication;
import com.example.demoLogin.entity.LoanApplication.TrackStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LoanApplicationRepositoryTest {

    @Autowired
    private LoanRepository loanApplicationRepository;

    @Test
    void whenSavingNewApplication_thenIdIsNotNullAndRecordExists() {
        // Given
        LoanApplication newApplication = new LoanApplication();
        newApplication.setCompanyName("Test Company");
        newApplication.setLoanType("Business");
        newApplication.setLoanAmount(50000.0);

        // When
        LoanApplication savedApplication = loanApplicationRepository.save(newApplication);

        // Then
        assertThat(savedApplication.getApplicationId()).isNotNull();
        assertThat(loanApplicationRepository.findById(savedApplication.getApplicationId())).isPresent();
    }

    @Test
    void whenFindByCompanyName_thenReturnCorrectApplications() {
        // Given
        LoanApplication app1 = new LoanApplication();
        app1.setCompanyName("Tech Solutions");
        app1.setLoanType("SME");
        app1.setLoanAmount(100000.0);
        loanApplicationRepository.save(app1);

        LoanApplication app2 = new LoanApplication();
        app2.setCompanyName("Tech Solutions");
        app2.setLoanType("Working Capital");
        app2.setLoanAmount(250000.0);
        loanApplicationRepository.save(app2);

        LoanApplication app3 = new LoanApplication();
        app3.setCompanyName("Another Company");
        app3.setLoanType("Equipment");
        app3.setLoanAmount(75000.0);
        loanApplicationRepository.save(app3);

        // When
        List<LoanApplication> techApps = Collections.singletonList(loanApplicationRepository.findByCompanyName("Tech Solutions"));

        // Then
        assertThat(techApps).hasSize(2);
        assertThat(techApps.get(0).getCompanyName()).isEqualTo("Tech Solutions");
        assertThat(techApps.get(1).getCompanyName()).isEqualTo("Tech Solutions");
    }

    @Test
    void whenSaving_thenDateAndStatusAreSetCorrectly() {
        // Given
        LoanApplication newApplication = new LoanApplication();
        newApplication.setCompanyName("Auto-Set Test");
        newApplication.setLoanType("Test");
        newApplication.setLoanAmount(100.0);

        // When
        LoanApplication savedApplication = loanApplicationRepository.save(newApplication);

        // Then
        assertThat(savedApplication.getTrackStatus()).isEqualTo(TrackStatus.PENDING);
        assertThat(savedApplication.getDate()).isNotNull();
    }
}