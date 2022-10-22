package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.service.CardService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/card")
public class CardController {
    final
    CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CardDto createCard(@Valid @RequestBody CardDto card){
        return cardService.saveCard(card);
    }
}
