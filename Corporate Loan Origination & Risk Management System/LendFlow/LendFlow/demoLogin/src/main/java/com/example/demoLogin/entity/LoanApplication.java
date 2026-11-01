package com.example.demoLogin.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;
//    @Column(unique = true)
    private String companyName;
    private String loanType;
    private double loanAmount;
    @Enumerated(EnumType.STRING)
    private TrackStatus trackStatus;
    private LocalDateTime date;

    public enum TrackStatus{
        PENDING,
        REJECTED,
        APPROVED
    }

    public LoanApplication() {
    }

    public LoanApplication(int applicationId, String companyName, String loanType, double loanAmount, TrackStatus trackStatus, LocalDateTime date) {
        this.applicationId = applicationId;
        this.companyName = companyName;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.trackStatus = trackStatus;
        this.date = date;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public TrackStatus getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(TrackStatus trackStatus) {
        this.trackStatus = trackStatus;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "LoanApplication{" +
                "applicationId=" + applicationId +
                ", companyName='" + companyName + '\'' +
                ", loanType='" + loanType + '\'' +
                ", loanAmount=" + loanAmount +
                ", trackStatus=" + trackStatus +
                ", date=" + date +
                '}';
    }

    @PrePersist
    public void setDate() {
        this.date = LocalDateTime.now();
    }
}
