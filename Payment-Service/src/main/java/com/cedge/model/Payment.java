package com.cedge.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String paymentReference;
	private Double amount;
	private Long payerAccountId;
	private Long payeeAccountId;
	private String status; // e.g., "PENDING", "COMPLETED", "FAILED"
	private LocalDateTime transactionDate;
	private Long customerId;
	
	

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getPayerAccountId() {
		return payerAccountId;
	}

	public void setPayerAccountId(Long payerAccountId) {
		this.payerAccountId = payerAccountId;
	}

	public Long getPayeeAccountId() {
		return payeeAccountId;
	}

	public void setPayeeAccountId(Long payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

}
