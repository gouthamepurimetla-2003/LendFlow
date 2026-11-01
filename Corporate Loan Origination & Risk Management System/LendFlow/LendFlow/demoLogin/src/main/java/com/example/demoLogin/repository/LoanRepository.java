package com.example.demoLogin.repository;

import com.example.demoLogin.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanApplication, Integer> {
    List<LoanApplication> findByCompanyNameContainingIgnoreCase(String companyName);
    Optional<LoanApplication> findByApplicationId(int applicationId);
}
