package com.example.demoLogin.service;

import com.example.demoLogin.entity.Disbursement;

import java.util.List;

public interface DisbursementService {
    Disbursement trackDisbursement(int applicationId, double amount, String repaymentSchedule);
    List<Disbursement> generateDisbursementReport();
}
