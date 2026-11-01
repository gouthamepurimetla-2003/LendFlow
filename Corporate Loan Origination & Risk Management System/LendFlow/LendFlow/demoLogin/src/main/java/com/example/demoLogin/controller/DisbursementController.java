//package com.example.demoLogin.controller;
//
//import com.example.demoLogin.entity.Disbursement;
//import com.example.demoLogin.service.DisbursementServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//@RestController
//public class DisbursementController {
//
//    @Autowired
//    DisbursementServiceImpl disbursementService;
//
//    @GetMapping("/track")
//    public ModelAndView trackPage() {
//        return new ModelAndView("track");
//    }
//
//    @PostMapping("/trackDisbursement1")
//    public ModelAndView trackDisbursement(@RequestParam("applicationId") int applicationId, @RequestParam("companyName") String companyName) {
//        System.out.println("inside trackDisbursement1 controller");
//        Disbursement disbursement = disbursementService.trackDisbursement(applicationId, companyName);
//
////        if (disbursement != null) {
//        ModelAndView m = new ModelAndView("trackDisbursement");
////        } else {
////            return null;
////        }
//        m.addObject(disbursement);
//        return m;
//    }
//
//}



package com.example.demoLogin.controller;

import com.example.demoLogin.entity.Disbursement;
import com.example.demoLogin.service.DisbursementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/disbursement")
public class DisbursementController {
    @Autowired
    private DisbursementServiceImpl disbursementService;

    @GetMapping("/track")
    public ModelAndView page(){
        return new ModelAndView("disbursement");
    }

    @PostMapping("/track")
    public Disbursement trackDisbursement(@RequestParam int applicationId,
                                          @RequestParam double amount,
                                          @RequestParam String repaymentSchedule) {
        System.out.println("inside trackDisbursement controller");
        return disbursementService.trackDisbursement(applicationId, amount, repaymentSchedule);
    }

    @GetMapping("/report")
    public List<Disbursement> generateDisbursementReport() {
        return disbursementService.generateDisbursementReport();
    }
}
