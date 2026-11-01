package com.example.demoLogin.service;


import com.example.demoLogin.entity.CreditEvaluation;
import com.example.demoLogin.entity.LoanApplication;
import com.example.demoLogin.repository.CreditEvaluationRepo;
import com.example.demoLogin.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CreditEvaluationServiceImp implements CreditEvaluationService{

    @Autowired
    CreditEvaluationRepo creditEvaluationRepo;

    @Autowired
    LoanRepository loanApplicationRepo;

    @Override
    public CreditEvaluation evaluateCredit(int applicationId) {
        Optional<CreditEvaluation> existingEvaluation = creditEvaluationRepo.findByApplicationId(applicationId);
        if (existingEvaluation.isPresent()) {
            return existingEvaluation.get();
        }
        Optional<LoanApplication> loanApplicationOpt = loanApplicationRepo.findById(applicationId);
        if (loanApplicationOpt.isEmpty()) {
            throw new RuntimeException("Loan application not found");
        }

        Optional<LoanApplication> loanApplication = loanApplicationRepo.findById(applicationId);

        int creditScore = new Random().nextInt(300, 500) + 300;
        double riskScore = calculateRiskScore(creditScore);

        CreditEvaluation evaluation = new CreditEvaluation();
        evaluation.setApplicationId(applicationId);
        evaluation.setCreditScore(creditScore);
        evaluation.setRiskScore(riskScore);
        evaluation.setEvaluationDate(LocalDateTime.now());

        return creditEvaluationRepo.save(evaluation);
    }

    private double calculateRiskScore(int creditScore) {
        creditScore = Math.max(300, Math.min(creditScore, 850));
        double normalizedCredit = (creditScore - 300.0) / (850.0 - 300.0);
        double riskScore = (1.0 - normalizedCredit) * 10.0;
        return Math.round(riskScore * 100.0) / 100.0;
    }


    @Override
    public double getRiskScore(int applicationId) {
        System.out.println("getting riskscore");
        return creditEvaluationRepo.findByApplicationId(applicationId).get().getRiskScore();
    }
}

