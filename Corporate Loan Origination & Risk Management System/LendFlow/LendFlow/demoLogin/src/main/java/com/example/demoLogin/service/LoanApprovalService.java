package com.example.demoLogin.service;

import com.example.demoLogin.entity.Approval;

public interface LoanApprovalService {
    Approval approveLoan(int applicationId, int approverId, int approvalLevel, String comments);
    Approval rejectLoan(int applicationId, int approverId, int approvalLevel, String comments);
    Approval createOrUpdateApproval(int applicationId, int approverId, int approvalLevel, String comments);
    Approval getApprovalStatus(int applicationId, int approvalLevel) throws Exception;


}
