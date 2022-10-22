package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.CardDto;

import java.util.List;

public interface CardService {
    CardDto saveCard(CardDto cardDto);
    List<CardDto> findAll();
    //CardDto findByPan(long pan);
}
