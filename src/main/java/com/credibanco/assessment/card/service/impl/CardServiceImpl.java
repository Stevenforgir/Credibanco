package com.credibanco.assessment.card.service.impl;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.mapper.impl.CardDaoMapperImpl;
import com.credibanco.assessment.card.mapper.impl.CardDtoMapperImpl;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.repository.ICardRepository;
import com.credibanco.assessment.card.service.CardService;
import com.credibanco.assessment.card.mapper.CardDaoMapper;
import com.credibanco.assessment.card.mapper.CardDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    CardDaoMapper cardDaoMapper = new CardDaoMapperImpl();
    CardDtoMapper cardDtoMapper = new CardDtoMapperImpl();
    @Autowired
    ICardRepository cardRepository;

    @Override
    public CardDto saveCard(CardDto cardDto) {
        //Card card = cardRepository.findByPan(cardDto.getPan());
        //if(card == null){
            Card card = cardRepository.save(cardDaoMapper.toDao(cardDto));
            cardDto = cardDtoMapper.toDto(card);
            return cardDto;
        //}else{
        //    return cardDto;
        //}
    }

    @Override
    public List<CardDto> findAll() {
        List<Card> cardList = cardRepository.findAll();
        List<CardDto> cardDtoList = new ArrayList<>();
        cardList.stream().forEach(card -> {
            cardDtoList.add(cardDtoMapper.toDto(card));
        });
        return cardDtoList;
    }

    //@Override
    //public CardDto findByPan(long pan) {
     //   CardDto cardDto;
      //  cardDto = cardDtoMapper.toDto(cardRepository.findByPan(Integer.valueOf(pan)));
       // return userDto;
    //}
}
