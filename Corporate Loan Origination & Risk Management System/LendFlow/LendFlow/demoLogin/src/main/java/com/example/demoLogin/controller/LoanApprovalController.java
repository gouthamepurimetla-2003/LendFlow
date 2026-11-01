package com.example.demoLogin.controller;

import com.example.demoLogin.service.LoanApprovalServiceImp;
import com.example.demoLogin.entity.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

    @RestController
    public class LoanApprovalController {

        @Autowired
        private LoanApprovalServiceImp loanApprovalServiceImp;

        @GetMapping("/approval")
        public ModelAndView showApprovalpage(){
            System.out.println("Approval Page....!!!!");
            return new ModelAndView("ApprovalPage");
        }

        @GetMapping("/approverLogin")
        public ModelAndView approverLoginPage(){
            System.out.println("Approver Login Page....!!!!");
            return new ModelAndView("Approver");
        }

        @GetMapping("/approvalPage")
        public ModelAndView approverHomePage(){
            System.out.println("Approver Home Page....!!!!");
            return new ModelAndView("ApprovalHome");
        }

        @GetMapping("/approverdocs")
        public ModelAndView approverDocsPage(){
            System.out.println("Approver Docs Page....!!!!");
            return new ModelAndView("ApproverViewDocs");
        }

        @GetMapping("/approvalinsert")
        public ModelAndView approvalInsertPage() {
            System.out.println("Approval Insert Page....!!!!");
            return new ModelAndView("ApprovalPage");
        }

        @PostMapping("/approve")
        public ResponseEntity<?> approveLoan(
                @RequestParam int applicationId,
                @RequestParam int approverId,
                @RequestParam int approvalLevel,
                @RequestParam(required = false) String comments) {
            try {
                Approval approval = loanApprovalServiceImp.approveLoan(applicationId, approverId, approvalLevel, comments);
                return ResponseEntity.ok(approval);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
            }
        }

        @PostMapping("/reject")
        public ResponseEntity<?> rejectLoan(
                @RequestParam int applicationId,
                @RequestParam int approverId,
                @RequestParam int approvalLevel,
                @RequestParam(required = false) String comments) {
            try {
                Approval approval = loanApprovalServiceImp.rejectLoan(applicationId, approverId, approvalLevel, comments);
                return ResponseEntity.ok(approval);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
            }
        }

        @GetMapping("/{applicationId}/level/{approvalLevel}")
        public ResponseEntity<?> getApprovalStatus(
                @PathVariable int applicationId,
                @PathVariable int approvalLevel) {
            try {
                Approval approval = loanApprovalServiceImp.getApprovalStatus(applicationId, approvalLevel);
                return ResponseEntity.ok(approval);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }


    }
