package com.example.demoLogin.repository;

import com.example.demoLogin.entity.Disbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisbursementRepo extends JpaRepository<Disbursement, Integer> {
}
