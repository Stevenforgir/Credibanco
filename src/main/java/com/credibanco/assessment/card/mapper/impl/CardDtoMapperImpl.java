package com.credibanco.assessment.card.mapper.impl;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.mapper.CardDtoMapper;
import com.credibanco.assessment.card.model.Card;

public class CardDtoMapperImpl implements CardDtoMapper {
    @Override
    public CardDto toDto(Card card){
        CardDto cardDto = new CardDto();
        cardDto.setIdentification(card.getIdentification());
        cardDto.setPan(card.getPan());
        cardDto.setOwner(card.getOwner());
        cardDto.setPhone(card.getPhone());
        cardDto.setType(card.isType());
        return cardDto;
    }
}
