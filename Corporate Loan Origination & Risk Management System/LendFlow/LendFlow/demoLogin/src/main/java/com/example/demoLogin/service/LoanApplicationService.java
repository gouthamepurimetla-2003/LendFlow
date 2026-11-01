package com.example.demoLogin.service;

import com.example.demoLogin.entity.LoanApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LoanApplicationService {

    void submitApplication(LoanApplication loanApplication);
    List<LoanApplication> getApplicationsByCompanyName(String companyName);
    Optional<LoanApplication> getApplicationByApplicationId(int applicationId);

}
