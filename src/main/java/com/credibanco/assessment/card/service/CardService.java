package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.model.Card;

public interface CardService {
    CardDto saveCard(CardDto cardDto);
}
