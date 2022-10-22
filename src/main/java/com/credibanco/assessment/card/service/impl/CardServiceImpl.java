package com.credibanco.assessment.card.service.impl;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.repository.ICardRepository;
import com.credibanco.assessment.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    ICardRepository cardRepository;

    @Override
    public CardDto saveCard(CardDto c) {
        return cardRepository.save(c);
    }
}
