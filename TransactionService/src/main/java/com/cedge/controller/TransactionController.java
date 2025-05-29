package com.cedge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cedge.dto.TransactionRequest;
import com.cedge.entity.Transaction;
import com.cedge.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transfer")
	public ResponseEntity<String> transferMoney(@RequestBody TransactionRequest request) {
		Long fromAccountId = request.getFromAccountId();
		Long toAccountId = request.getToAccountId();
		double amount = request.getAmount();
		String transactionStatus = transactionService.transferMoney(fromAccountId, toAccountId, amount);
		return ResponseEntity.ok(transactionStatus);
	}

	@GetMapping("/{id}")
	public ResponseEntity<com.cedge.entity.Customer> getCustomerById(@PathVariable Long id) {
		com.cedge.entity.Customer account = transactionService.getCustomerDetails(id);
		return ResponseEntity.ok(account);
	}

	@GetMapping("/checkBalance/{accountId}")
	public ResponseEntity<Double> checkBalance(@PathVariable Long accountId) {
		double balance = transactionService.checkAccountBalance(accountId);
		return ResponseEntity.ok(balance);
	}

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		Transaction savedTransaction = transactionService.createTransaction(transaction);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
	}

	@GetMapping("/account/{accountId}")
	public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable Long accountId) {
		List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
		return ResponseEntity.ok(transactions);
	}
	
	

}
