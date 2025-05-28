package com.cedge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cedge.model.Card;
import com.cedge.service.CardService;

@RestController
@RequestMapping("/api/cards")
public class CardController {
	
	private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/issue")
    public Card issueCard(@RequestBody Card card) {
        return cardService.issueNewCard(card);
    }

    @GetMapping("/{cardNumber}")
    public Optional<Card> getCardByNumber(@PathVariable String cardNumber) {
        return cardService.getCardByNumber(cardNumber);
    }

    @GetMapping("/customer/{customerId}")
    public List<Card> getAllCardsByCustomerId(@PathVariable Long customerId) {
        return cardService.getAllCardsByCustomerId(customerId);
    }

    @PutMapping("/update-status")
    public Card updateCardStatus(@RequestParam String cardNumber, @RequestParam String status) {
        return cardService.updateCardStatus(cardNumber, status);
    }

}
