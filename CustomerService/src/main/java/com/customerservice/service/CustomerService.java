package com.customerservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerservice.model.Customer;
import com.customerservice.repo.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setAddress(customerDetails.getAddress());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customerRepository.delete(customer);
    }

}
