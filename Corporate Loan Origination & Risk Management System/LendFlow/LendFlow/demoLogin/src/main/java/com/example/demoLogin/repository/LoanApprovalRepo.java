package com.example.demoLogin.repository;

import com.example.demoLogin.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanApprovalRepo extends JpaRepository<Approval, Integer> {
    Optional<Approval> findByApplicationIdAndApprovalLevel(int applicationId, int approvalLevel);
}




