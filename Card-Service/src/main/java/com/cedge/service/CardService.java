package com.cedge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cedge.model.Card;
import com.cedge.repo.CardRepository;

@Service
public class CardService {
	
	private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card issueNewCard(Card card) {
        return cardRepository.save(card);
    }

    public Optional<Card> getCardByNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    public List<Card> getAllCardsByCustomerId(Long customerId) {
        return cardRepository.findAll().stream()
            .filter(card -> card.getCustomerId().equals(customerId))
            .toList();
    }

    public Card updateCardStatus(String cardNumber, String status) {
        Optional<Card> card = cardRepository.findByCardNumber(cardNumber);
        if (card.isPresent()) {
            Card updatedCard = card.get();
            updatedCard.setStatus(status);
            return cardRepository.save(updatedCard);
        }
        throw new IllegalArgumentException("Card not found with card number: " + cardNumber);
    }

}
