package com.cedge.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cedge.entity.Account;
import com.cedge.entity.Customer;
import com.cedge.entity.Transaction;
import com.cedge.repo.TransactionRepo;

import ch.qos.logback.classic.Logger;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepo transactionRepository;

	@Autowired
	private RestTemplate restTemplate;

	// URL patterns for Account and Customer services
	private static final String ACCOUNT_SERVICE_URL = "http://ACCOUNT-SERVICE/accounts/";
	private static final String CUSTOMER_SERVICE_URL = "http://CUSTOMER-SERVICE/customers/";

	public String transferMoney(Long fromAccountId, Long toAccountId, double amount) {
		// Fetch from account details
		Account fromAccount = restTemplate.getForObject(ACCOUNT_SERVICE_URL + fromAccountId, Account.class);
		
		
		
		System.out.println(fromAccount.toString());
		
		System.out.println(fromAccount.getId());
		System.out.println(fromAccount.getAccountNumber());
		System.out.println(fromAccount.getBalance());
		System.out.println(fromAccount.getAccountType());
		System.out.println(fromAccount.getCustomerId());
		
		
		// Fetch to account details
		Account toAccount = restTemplate.getForObject(ACCOUNT_SERVICE_URL + toAccountId, Account.class);

		System.out.println(toAccount.getId());
		System.out.println(toAccount.getAccountNumber());
		System.out.println(toAccount.getBalance());
		System.out.println(toAccount.getAccountType());
		System.out.println(toAccount.getCustomerId());
		
		
		// Check the balance of the sender account
		if (fromAccount.getBalance() < amount) {
			return "Insufficient balance!";
		}

		// Perform the transfer (update balances)
		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		// Save the updated accounts
		restTemplate.put(ACCOUNT_SERVICE_URL + fromAccountId + "/balance", fromAccount.getBalance());
		restTemplate.put(ACCOUNT_SERVICE_URL + toAccountId + "/balance", toAccount.getBalance());

		return "Transfer Successful!";
	}

	public Customer getCustomerDetails(Long customerId) {
		// Fetch customer details from Customer Service
		return restTemplate.getForObject(CUSTOMER_SERVICE_URL + customerId, Customer.class);
	}

	public double checkAccountBalance(Long accountId) {
		String accountServiceUrl = "http://ACCOUNT-SERVICE/accounts/getAccountBalanceByAccountId/" + accountId;
		ResponseEntity<Double> response = restTemplate.getForEntity(accountServiceUrl, Double.class);
		return response.getBody();
	}

	public Transaction createTransaction(Transaction transaction) {
		transaction.setTransactionDate(LocalDateTime.now());
		return transactionRepository.save(transaction);
	}

	public List<Transaction> getTransactionsByAccountId(Long accountId) {
		return transactionRepository.findByAccountId(accountId);
	}

}
