package com.example.demoLogin.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Disbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int disbursementId;
    @Column(name="application_id", unique = true, nullable = true, insertable = false, updatable = false)
    private int applicationId;
    private double disbursedAmount;
    private LocalDateTime date;
    private String repaymentSchedule;

    @OneToOne
    @JoinColumn(name = "application_id")
    private LoanApplication application;

    public Disbursement() {
    }

    public Disbursement(int disbursementId, int applicationId, double disbursedAmount, LocalDateTime date, String repaymentSchedule, LoanApplication application) {
        this.disbursementId = disbursementId;
        this.applicationId = applicationId;
        this.disbursedAmount = disbursedAmount;
        this.date = date;
        this.repaymentSchedule = repaymentSchedule;
        this.application = application;
    }

    public int getDisbursementId() {
        return disbursementId;
    }

    public void setDisbursementId(int disbursementId) {
        this.disbursementId = disbursementId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public double getDisbursedAmount() {
        return disbursedAmount;
    }

    public void setDisbursedAmount(double disbursedAmount) {
        this.disbursedAmount = disbursedAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = LocalDateTime.now();
    }

    public String getRepaymentSchedule() {
        return repaymentSchedule;
    }

    public void setRepaymentSchedule(String repaymentSchedule) {
        this.repaymentSchedule = repaymentSchedule;
    }

    public LoanApplication getApplication() {
        return application;
    }

    public void setApplication(LoanApplication application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Disbursement{" +
                "disbursementId=" + disbursementId +
                ", applicationId=" + applicationId +
                ", disbursedAmount=" + disbursedAmount +
                ", date=" + date +
                ", repaymentSchedule='" + repaymentSchedule + '\'' +
                ", application=" + application +
                '}';
    }
}
