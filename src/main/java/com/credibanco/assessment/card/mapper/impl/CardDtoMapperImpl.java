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
        cardDto.setMaskedPan(card.getMaskedPan());
        cardDto.setOwner(card.getOwner());
        cardDto.setPhone(card.getPhone());
        cardDto.setType(card.getType());
        cardDto.setCreated(card.isCreated());
        cardDto.setValidationNumber(card.getValidationNumber());
        cardDto.setActivated(card.isActivated());
        return cardDto;
    }
}
