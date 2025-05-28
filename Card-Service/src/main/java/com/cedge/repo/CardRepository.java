package com.cedge.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cedge.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);
}
