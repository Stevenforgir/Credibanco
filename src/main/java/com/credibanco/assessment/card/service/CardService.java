package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.CardDtoActivateRequest;
import com.credibanco.assessment.card.dto.CardDtoActivateResponse;
import com.credibanco.assessment.card.dto.CardDtoResponse;
import com.credibanco.assessment.card.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CardService {
    CardDtoResponse saveCard(CardDto cardDto);
    CardDtoActivateResponse activateCard(CardDtoActivateRequest cardDto);
    List<CardDto> findAll();
    ArrayList<CardDto> getAllCard();
    CardDto findByPan(long pan);
}
