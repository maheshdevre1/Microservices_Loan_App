package com.customerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customerservice.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // You can define custom query methods here, if needed
}
