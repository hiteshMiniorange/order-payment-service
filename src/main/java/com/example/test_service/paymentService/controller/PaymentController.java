package com.example.test_service.paymentService.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody Double amount){

        if (amount > 1000){
            log.info("Payment Failed: Order value exceed limit");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FAILED");
        }
        log.info("Payment has been successful");
        return ResponseEntity.ok("SUCCESS");
    }
}
