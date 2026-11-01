package com.example.demoLogin.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int approvalId;
    @Column(unique = true, nullable = false)
    private int applicationId;
    private int approverId;
    private int approvalLevel;
    public enum ApprovalStatus{
        PENDING,
        REJECTED,
        APPROVED
    }
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;
    private String comments;
    private LocalDateTime approvalDate;

    @OneToOne
    @JoinColumn(insertable = false, updatable = false)
    LoanApplication application;

    public Approval() {
    }

    public Approval(int approvalId, int applicationId, int approverId, int approvalLevel, String comments, LocalDateTime approvalDate) {
        this.approvalId = approvalId;
        this.applicationId = applicationId;
        this.approverId = approverId;
        this.approvalLevel = approvalLevel;
        this.comments = comments;
        this.approvalDate = approvalDate;
        this.approvalStatus = ApprovalStatus.PENDING;
    }

    public int getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(int approvalId) {
        this.approvalId = approvalId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    public int getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(int approvalLevel) {
        this.approvalLevel = approvalLevel;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = LocalDateTime.now();
    }

    public LoanApplication getApplication() {
        return application;
    }

    public void setApplication(LoanApplication application) {
        this.application = application;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }
    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

//    public void setApprovalStatus(String status) {
//        if(status.equalsIgnoreCase("APPROVED")){
//            this.approvalStatus = ApprovalStatus.APPROVED;
//        } else if(status.equalsIgnoreCase("REJECTED")){
//            this.approvalStatus = ApprovalStatus.REJECTED;
//        } else{
//            this.approvalStatus = ApprovalStatus.PENDING;
//        }
//    }}
}
