package com.example.demoLogin.service;

import com.example.demoLogin.entity.Approval;
import com.example.demoLogin.entity.LoanApplication;
import com.example.demoLogin.repository.LoanApprovalRepo;
import com.example.demoLogin.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoanApprovalServiceImp implements LoanApprovalService{

    @Autowired
    public LoanApprovalRepo loanApprovalRepo;

    @Autowired
    public LoanRepository loanApplicationRepository;


    public Approval approveLoan(int applicationId, int approverId, int approvalLevel, String comments) {
        Approval approval = createOrUpdateApproval(applicationId, approverId, approvalLevel, comments);
        approval.setApprovalStatus(Approval.ApprovalStatus.APPROVED);
        Optional<LoanApplication> optionalApplication = loanApplicationRepository.findById(applicationId);
        if (optionalApplication.isPresent()) {
            LoanApplication loanApplication = optionalApplication.get();
            loanApplication.setTrackStatus(LoanApplication.TrackStatus.valueOf("APPROVED"));
            loanApplication.setDate();
            loanApplicationRepository.save(loanApplication);
        }
        return loanApprovalRepo.save(approval);
    }

    public Approval rejectLoan(int applicationId, int approverId, int approvalLevel, String comments) {
        Approval approval = createOrUpdateApproval(applicationId, approverId, approvalLevel, comments);
        approval.setApprovalStatus(Approval.ApprovalStatus.REJECTED);
        Optional<LoanApplication> optionalApplication = loanApplicationRepository.findById(applicationId);
        if (optionalApplication.isPresent()) {
            LoanApplication loanApplication = optionalApplication.get();
            loanApplication.setTrackStatus(LoanApplication.TrackStatus.valueOf("REJECTED"));
            loanApplication.setDate();
            loanApplicationRepository.save(loanApplication);
        }
        return loanApprovalRepo.save(approval);
    }

    public Approval createOrUpdateApproval(int applicationId, int approverId, int approvalLevel, String comments) {
        Optional<Approval> existingApproval = loanApprovalRepo.findByApplicationIdAndApprovalLevel(applicationId, approvalLevel);

        Approval approval;
        if (existingApproval.isPresent()) {
            approval = existingApproval.get();
        } else {
            approval = new Approval();
            approval.setApplicationId(applicationId);
            approval.setApprovalLevel(approvalLevel);
        }

        approval.setApproverId(approverId);
        approval.setComments(comments);
        approval.setApprovalDate(LocalDateTime.now());

        return approval;
    }

    public Approval getApprovalStatus(int applicationId, int approvalLevel) throws Exception {
        return loanApprovalRepo.findByApplicationIdAndApprovalLevel(applicationId, approvalLevel)
                .orElseThrow(() -> new Exception("Approval not found"));
    }
}
