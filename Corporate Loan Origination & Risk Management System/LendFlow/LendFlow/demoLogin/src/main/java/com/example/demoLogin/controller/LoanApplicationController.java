package com.example.demoLogin.controller;

import com.example.demoLogin.entity.LoanApplication;
import com.example.demoLogin.service.LoanApplicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class LoanApplicationController {

    @Autowired
    public LoanApplicationServiceImpl loanApplicationServiceImpl;

//    @GetMapping("/home1")
//    public ModelAndView homePage(){
//        System.out.println("Running...!!");
//        return new ModelAndView("index");
//    }

    @GetMapping("/success")
    public ModelAndView newApplication(){
        System.out.println("New Application...!!");
        return new ModelAndView("ApplicationPage");
    }

    @PostMapping("/submit1")
    public ResponseEntity<LoanApplication> submitApplication(
            @RequestParam("companyName") String companyName,
            @RequestParam("loanAmount") double loanAmount,
            @RequestParam("loanType") String loanType) {

        LoanApplication loanApplication = new LoanApplication();

        loanApplication.setCompanyName(companyName);
        loanApplication.setLoanAmount(loanAmount);
        loanApplication.setLoanType(loanType);
        loanApplicationServiceImpl.submitApplication(loanApplication);

        return ResponseEntity.status(HttpStatus.CREATED).body(loanApplication);
    }

    @GetMapping("/done")
    public ModelAndView donePage(){
        return new ModelAndView("SuccessPage");
    }

    @GetMapping("/search/{companyName}")
    public List<LoanApplication> viewApplicationDetails(@PathVariable(name = "companyName") String companyName) {
        return loanApplicationServiceImpl.getApplicationsByCompanyName(companyName);
    }

    @GetMapping("/search/id/{applicationId}")
    public Optional<LoanApplication> trackApplicationStatus(@PathVariable(name = "applicationId") int applicationId) {
        return loanApplicationServiceImpl.getApplicationByApplicationId(applicationId);
    }
    @GetMapping("/uploadPage")
    public ModelAndView documentUpload(){
        System.out.println("UploadPage Application...!!");
        return new ModelAndView("uploadmultiple");
    }
}

