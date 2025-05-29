package com.cedge.model;

import java.util.List;

import com.cedge.model.Loan;
import com.cedge.model.Payment;

public class CustomerReport {

	private Long customerId;
	private List<Loan> loans;
	private List<Payment> payments;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "CustomerReport [customerId=" + customerId + ", loans=" + loans + ", payments=" + payments + "]";
	}
	
	

}
