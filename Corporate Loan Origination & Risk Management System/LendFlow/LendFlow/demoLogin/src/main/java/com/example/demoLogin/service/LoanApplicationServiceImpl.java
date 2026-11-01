package com.example.demoLogin.service;

import com.example.demoLogin.entity.LoanApplication;
import com.example.demoLogin.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public void submitApplication(LoanApplication loanApplication) {
        loanApplication.setTrackStatus(LoanApplication.TrackStatus.PENDING);
        loanRepository.save(loanApplication);
    }

    public List<LoanApplication> getApplicationsByCompanyName(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            return loanRepository.findAll();
        }
        return loanRepository.findByCompanyNameContainingIgnoreCase(companyName);
    }
    public Optional<LoanApplication> getApplicationByApplicationId(int applicationId) {
        return loanRepository.findById(applicationId);
    }


}
