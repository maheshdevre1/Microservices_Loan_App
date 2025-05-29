package com.cedge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedge.entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	  List<Transaction> findByAccountId(Long accountId);

}
