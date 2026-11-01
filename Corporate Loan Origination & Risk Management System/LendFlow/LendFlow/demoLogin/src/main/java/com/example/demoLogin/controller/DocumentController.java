package com.example.demoLogin.controller;

import com.example.demoLogin.entity.Document;
import com.example.demoLogin.service.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    DocumentServiceImpl documentService;



    @GetMapping("/upload")
    public ModelAndView uploadPage(){
        System.out.println("UploadPage");
        return new ModelAndView("upload");
    }

    @PostMapping("/upload/{applicationId}")
    public ResponseEntity<Document> uploadDocument(
            @PathVariable int applicationId,
            @RequestParam String documentType,
            @RequestParam("file") MultipartFile file) {
        System.out.println("Uploaded file....!!!!");
        try {
            if(!documentService.validateDocumentFormat(file.getOriginalFilename())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Document document = documentService.uploadDocument(applicationId, documentType, file);
            return new ResponseEntity<>(document, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/uploadmultiple")
    public ModelAndView uploadMultiplePage(){
        System.out.println("UploadMultiplePage");
        return new ModelAndView("uploadmultiple");
    }

    @PostMapping("/uploadmultiple/{applicationId}")
    public ResponseEntity<List<Document>> uploadMultipleDocuments(
            @PathVariable int applicationId,
            @RequestParam("documentTypes") String[] documentTypes,
            @RequestParam("files") MultipartFile[] files) {
        System.out.println("Uploading ... Multiple Files...!!");
        try {
            // Validate that we have the same number of files and document types
            if (files.length != documentTypes.length) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            for (MultipartFile file : files) {
                if (!documentService.validateDocumentFormat(file.getOriginalFilename())) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }

            List<Document> documents = documentService.uploadMultipleDocuments(applicationId, documentTypes, files);
            return new ResponseEntity<>(documents, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<Document>> getDocumentsByApplicationId(@PathVariable int applicationId) {
        List<Document> documents = documentService.viewUploadedDocuments(applicationId);

        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PutMapping("/{documentId}/validate")
    public ResponseEntity<String> validateDocument(@PathVariable Long documentId, @RequestParam Boolean isValid) {
        Optional<Document> updatedDocument = documentService.validateDocument(documentId, isValid);

        if (updatedDocument.isPresent()) {
            return new ResponseEntity<>("Document status updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Document not found.", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/login")
    public ModelAndView loginCheck(@RequestParam("approver_id") String approverId, @RequestParam("password") String password) {

        System.out.println("\nChecking...!!!");

        if (approverId.equals("101") && password.equals("101")) {
            System.out.println("Success...!!!");
            return new ModelAndView("ApprovalHome");
        } else {
            System.out.println("Failure...!!!");
            return new ModelAndView("FailurePage");
        }
    }

    @GetMapping("/view")
    public ModelAndView viewDocuments() {
        System.out.println("Viewing Documents....!!");
        return new ModelAndView("viewDocs");
    }
}
