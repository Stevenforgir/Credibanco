package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.CardDtoActivateRequest;
import com.credibanco.assessment.card.dto.CardDtoActivateResponse;
import com.credibanco.assessment.card.dto.CardDtoResponse;
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
    public CardDtoResponse saveCard(@Valid @RequestBody CardDto card){
        return cardService.saveCard(card);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/activate")
    public CardDtoActivateResponse activateCard(@Valid @RequestBody CardDtoActivateRequest cardDtoActivate){
        return cardService.activateCard(cardDtoActivate);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/checking")
    public CardDtoActivateResponse checkCard(@Valid @RequestBody CardDtoActivateRequest cardDtoActivate){
        return cardService.activateCard(cardDtoActivate);
    }
}
