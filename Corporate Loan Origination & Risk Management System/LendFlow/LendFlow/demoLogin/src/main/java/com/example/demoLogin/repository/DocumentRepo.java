package com.example.demoLogin.repository;

import com.example.demoLogin.entity.Document;
import com.example.demoLogin.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Long> {
    List<Document> findByApplication(LoanApplication application);

}
