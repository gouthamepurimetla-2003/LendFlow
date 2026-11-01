package com.example.demoLogin.service;

import com.example.demoLogin.entity.Disbursement;
import com.example.demoLogin.repository.DisbursementRepo;
import com.example.demoLogin.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisbursementServiceImpl implements DisbursementService {
    @Autowired
    private DisbursementRepo disbursementRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Disbursement trackDisbursement(int applicationId, double amount, String repaymentSchedule) {
        Disbursement disbursement = new Disbursement();
        disbursement.setApplicationId(applicationId);
        disbursement.setDisbursedAmount(amount);
        disbursement.setRepaymentSchedule(repaymentSchedule);
        disbursement.setDate(java.time.LocalDateTime.now());
        disbursement.setApplication(loanRepository.findByApplicationId(applicationId).get());
        return disbursementRepository.save(disbursement);
    }

    @Override
    public List<Disbursement> generateDisbursementReport() {
        return disbursementRepository.findAll();
    }
}
