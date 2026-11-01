package com.example.demoLogin.service;

import com.example.demoLogin.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document uploadDocument(int applicationId, String documentType, MultipartFile file) throws IOException;
    List<Document> viewUploadedDocuments(int applicationId);
    Optional<Document> validateDocument(Long documentId, Boolean isValid);
    boolean validateDocumentFormat(String fileName);
    List<Document> uploadMultipleDocuments(int applicationId, String[] documentType, MultipartFile[] files) throws IOException;

}
