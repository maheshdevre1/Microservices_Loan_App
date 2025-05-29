package com.cedge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cedge.model.Payment;
import com.cedge.model.PaymentRequest;
import com.cedge.model.PaymentResponse;
import com.cedge.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	
	private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public ResponseEntity<Payment> initiatePayment(@RequestBody Payment payment) {
        Payment initiatedPayment = paymentService.initiatePayment(payment);
        return new ResponseEntity<>(initiatedPayment, HttpStatus.CREATED);
    }

    @PutMapping("/complete/{paymentReference}")
    public ResponseEntity<Payment> completePayment(@PathVariable String paymentReference) {
        Payment completedPayment = paymentService.completePayment(paymentReference);
        return new ResponseEntity<>(completedPayment, HttpStatus.OK);
    }

    @GetMapping("/{paymentReference}")
    public ResponseEntity<Optional<Payment>> getPaymentStatus(@PathVariable String paymentReference) {
        Optional<Payment> paymentStatus = paymentService.getPaymentStatus(paymentReference);
        return new ResponseEntity<>(paymentStatus, HttpStatus.OK);
    }
    
    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) {
        // Simulate payment processing
        if (paymentRequest.getAmount() <= 0) {
            return ResponseEntity.ok(new PaymentResponse(false, "Invalid payment amount"));
        }

        // Simulate success response
        return ResponseEntity.ok(new PaymentResponse(true, "Payment processed successfully"));
    }
    
    
    //get payment details by customer id
    @GetMapping("/getPaymentDetails/{id}")
    public ResponseEntity<?> getPaymentDetails(@PathVariable Long id){
    	List<Payment> p = paymentService.getPaymentDetails(id);
    	return ResponseEntity.ok(p);
    }

}
