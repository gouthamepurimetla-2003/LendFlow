package com.example.demoLogin.controller;

import com.example.demoLogin.entity.CreditEvaluation;
import com.example.demoLogin.service.CreditEvaluationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
public class CreditEvaluationController {

    @Autowired
    CreditEvaluationServiceImp creditEvaluationServiceImp;

    @GetMapping("/credit")
    public ModelAndView homePage1(){
        System.out.println("Running...!!");
        return new ModelAndView("credit");
    }
    @PostMapping("/credit/{applicationId}")
    public ResponseEntity<CreditEvaluation> creditEvaluation(@PathVariable int applicationId) {
        System.out.println("Credit Evaluation in progress....!!!!");
        CreditEvaluation evaluated = creditEvaluationServiceImp.evaluateCredit(applicationId);
        return ResponseEntity.ok(evaluated);
    }
    @GetMapping("/riskScore/{applicationId}")
    public double getRiskScore(@PathVariable int applicationId) {
        System.out.println("Getting Risk Score....!!!!");
        return creditEvaluationServiceImp.getRiskScore(applicationId);
    }
}

