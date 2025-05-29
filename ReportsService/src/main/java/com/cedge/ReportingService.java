package com.cedge;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cedge.model.CustomerReport;
import com.cedge.model.Loan;
import com.cedge.model.LoanReport;
import com.cedge.model.Payment;

@Service
public class ReportingService {

	@Autowired
	private RestTemplate restTemplate;

	public CustomerReport generateCustomerReport(Long customerId) {
		// Fetch loan details
		String loanServiceUrl = "http://LOAN-SERVICE/loans/loandetailsByCid/" + customerId;
		System.out.println("loanServiceUrl ==> " + loanServiceUrl);
		Loan[] loans = restTemplate.getForObject(loanServiceUrl, Loan[].class);
		System.out.println("loans ==> " + loans);

		// Fetch payment details
		String paymentServiceUrl = "http://PAYMENT-SERVICE/api/payments/getPaymentDetails/" + customerId;
		System.out.println("paymentServiceUrl ==> " + paymentServiceUrl);
		Payment[] payments = restTemplate.getForObject(paymentServiceUrl, Payment[].class);

		// Generate report
		CustomerReport report = new CustomerReport();
		report.setCustomerId(customerId);
		report.setLoans(Arrays.asList(loans));
		report.setPayments(Arrays.asList(payments));

		return report;
	}

	public List<LoanReport> getActiveLoans() {
		String loanServiceUrl = "http://LOAN-SERVICE/loans/activeLoan";
		LoanReport[] activeLoans = restTemplate.getForObject(loanServiceUrl, LoanReport[].class);
		return Arrays.asList(activeLoans);
	}

}
