package com.bancobase.paymentsapi.controller;

import com.bancobase.paymentsapi.entity.Payment;
import com.bancobase.paymentsapi.entity.PaymentStatus;
import com.bancobase.paymentsapi.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name="Payments API")
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @Operation(summary = "Creates a payment resource given a valid request body payment data")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        var paymentCreated = paymentService.createPayment(payment);
        log.info("Payment created: {}", paymentCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentCreated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds and returns a payment data given a valid payment Id")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        var paymentFound = paymentService.getPaymentById(id);
        log.info("Payment found: {}", paymentFound);
        return ResponseEntity.status(HttpStatus.FOUND).body(paymentFound);
    }

    @PutMapping("/{id}/{status}")
    @Operation(summary = "Searches an updates payments status given a valid payment id and payment status")
    public ResponseEntity<Payment> updatePaymentStatus(@PathVariable Long id, @PathVariable PaymentStatus status) {
        var paymentUpdated = paymentService.updatePaymentStatus(id, status);
        log.info("Payment updated {}", paymentUpdated);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentUpdated);
    }
}
