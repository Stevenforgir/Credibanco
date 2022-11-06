package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.*;

import java.util.ArrayList;
import java.util.List;

public interface CardService {
    CardDtoResponse saveCard(CardDto cardDto);
    CardDtoActivateResponse activateCard(CardDtoActivateRequest cardDto);
    List<CardDto> findAll();
    ArrayList<CardDto> getAllCard();
    CardDto findByPan(long pan);

    CardDto findById(long id);

    CardDtoCheckResponse checkCard(CardDtoCheckRequest cardDtoCheck);

    CardDtoDeleteResponse deleteCard(CardDtoDeleteRequest cardDtoDelete);

}
