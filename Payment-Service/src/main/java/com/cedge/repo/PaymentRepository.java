package com.cedge.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cedge.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentReference(String paymentReference);
    
    //List<Payment> getPaymentByCid(Long customerId);
    
    @Query("SELECT p FROM Payment p WHERE p.customerId = :customerId")
    List<Payment> getPaymentByCid(@Param("customerId") Long customerId);

    
}
