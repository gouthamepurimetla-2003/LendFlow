package com.example.demoLogin.service;

import com.example.demoLogin.entity.CreditEvaluation;
import org.springframework.stereotype.Service;

public interface CreditEvaluationService {
    CreditEvaluation evaluateCredit(int applicationId);
    double getRiskScore(int applicationId);
}
