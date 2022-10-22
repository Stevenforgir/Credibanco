package com.credibanco.assessment.card.mapper;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.model.Card;

public interface CardDtoMapper {
    CardDto toDto(Card card);
}
