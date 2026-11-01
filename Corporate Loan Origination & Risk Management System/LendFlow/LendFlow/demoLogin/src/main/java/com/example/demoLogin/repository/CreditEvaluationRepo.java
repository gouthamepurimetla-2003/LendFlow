package com.example.demoLogin.repository;

import com.example.demoLogin.entity.CreditEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditEvaluationRepo extends JpaRepository<CreditEvaluation,Integer> {

    Optional<CreditEvaluation> findByApplicationId(int applicationId);
}