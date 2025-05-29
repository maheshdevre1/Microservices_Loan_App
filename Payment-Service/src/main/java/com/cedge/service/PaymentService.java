package com.cedge.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import com.cedge.model.Payment;
import com.cedge.repo.PaymentRepository;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;

	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public Payment initiatePayment(Payment payment) {
		payment.setStatus("PENDING");
		payment.setTransactionDate(LocalDateTime.now());
		return paymentRepository.save(payment);
	}

	public Payment completePayment(String paymentReference) {
		Optional<Payment> paymentOptional = paymentRepository.findByPaymentReference(paymentReference);
		if (paymentOptional.isPresent()) {
			Payment payment = paymentOptional.get();
			payment.setStatus("COMPLETED");
			return paymentRepository.save(payment);
		}
		throw new IllegalArgumentException("Payment not found with reference: " + paymentReference);
	}

	public Optional<Payment> getPaymentStatus(String paymentReference) {
		return paymentRepository.findByPaymentReference(paymentReference);
	}

	public List<Payment> getPaymentDetails(Long id) {
		List<Payment> payment = paymentRepository.getPaymentByCid(id);
		if(payment.isEmpty()) {
			throw new EntityNotFoundException("No payment found for customer ID: " + id);
		}
		return payment;
	}
	
	

}
