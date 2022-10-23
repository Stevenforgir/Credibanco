package com.credibanco.assessment.card.mapper.impl;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.mapper.CardDaoMapper;
import com.credibanco.assessment.card.model.Card;

public class CardDaoMapperImpl implements CardDaoMapper {
    @Override
    public Card toDao(CardDto cardDto){
        Card card = new Card();
        card.setIdentification(cardDto.getIdentification());
        card.setPan(cardDto.getPan());
        card.setOwner(cardDto.getOwner());
        card.setPhone(cardDto.getPhone());
        card.setType(cardDto.getType());
        card.setCreated(cardDto.isCreated());
        card.setValidationNumber(cardDto.getValidationNumber());
        card.setActivated(cardDto.isActivated());
        return card;
    }
}
