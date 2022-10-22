package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.repository.ICardRepository;
import com.credibanco.assessment.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private ICardRepository repo;
    final
    CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/save")
    public CardDto saveCard(@Valid @RequestBody CardDto card){
        return cardService.saveCard(card);
    }
}
