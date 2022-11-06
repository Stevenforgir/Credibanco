package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.dto.*;
import com.credibanco.assessment.card.repository.ICardRepository;
import com.credibanco.assessment.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/card")
@CrossOrigin("*")
public class CardController {

    @Autowired
    private ICardRepository repo;
    final
    CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public CardDtoResponse saveCard(@Valid @RequestBody CardDto card){
        return cardService.saveCard(card);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/activate")
    @ResponseStatus(HttpStatus.OK)
    public CardDtoActivateResponse activateCard(@Valid @RequestBody CardDtoActivateRequest cardDtoActivate){
        return cardService.activateCard(cardDtoActivate);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/check")
    @ResponseStatus(HttpStatus.OK)
    public CardDtoCheckResponse checkCard(@Valid @RequestBody CardDtoCheckRequest cardDtoCheck){
        return cardService.checkCard(cardDtoCheck);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public CardDtoDeleteResponse deleteUser(@Valid @RequestBody CardDtoDeleteRequest cardDtoDelete) {
        return cardService.deleteCard(cardDtoDelete);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CardDto> getCards() {
        return cardService.findAll();
    }
}
