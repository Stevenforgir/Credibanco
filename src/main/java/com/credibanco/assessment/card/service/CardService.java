package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CardService {
    CardDto saveCard(CardDto cardDto);
    List<CardDto> findAll();
    ArrayList<CardDto> getAllCard();
    Optional<CardDto> findCardByPan(long pan);
}
