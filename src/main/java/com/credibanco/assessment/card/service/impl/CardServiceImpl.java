package com.credibanco.assessment.card.service.impl;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.CardDtoActivateRequest;
import com.credibanco.assessment.card.dto.CardDtoActivateResponse;
import com.credibanco.assessment.card.dto.CardDtoResponse;
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
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    CardDaoMapper cardDaoMapper = new CardDaoMapperImpl();
    CardDtoMapper cardDtoMapper = new CardDtoMapperImpl();
    @Autowired
    ICardRepository cardRepository;

    @Override
    public CardDtoResponse saveCard(CardDto cardDto) {
        Card card = cardRepository.findByPan(cardDto.getPan());
        CardDtoResponse cardDtoResponse = new CardDtoResponse();
        String maskedCard = "";

        if(card == null) {
            cardDto.setCreated(true);
            int n = 100;
            cardDto.setValidationNumber((int) (Math.random() * n) + 1);

            String mp = cardDto.getPan() + "";
            String mask = "";

            mask = preMaskCardNumber(mp.length());
            maskedCard = maskCardNumber(mp, mask);

            Card saveCard = cardRepository.save(cardDaoMapper.toDao(cardDto));
            cardDto = cardDtoMapper.toDto(saveCard);

            cardDtoResponse.setMaskedPan(maskedCard);
            cardDtoResponse.setResponseCode("01");
            cardDtoResponse.setResultStatus("Éxito");
            cardDtoResponse.setValidationNumber(cardDto.getValidationNumber());

        }
        else{
            cardDtoResponse.setMaskedPan(maskedCard);
            cardDtoResponse.setResponseCode("00");
            cardDtoResponse.setResultStatus("Fallido");
            cardDtoResponse.setValidationNumber(0);

        }
        return cardDtoResponse;
    }

    @Override
    public CardDtoActivateResponse activateCard(CardDtoActivateRequest cardDto) {
        Card card = cardRepository.findByPan(cardDto.getPan());
        CardDtoActivateResponse cardDtoActivateResponse = new CardDtoActivateResponse();
        String maskedCard = "";
        System.out.println("datos ingresados para activar: " + card.getPan());

        if(card == null) {
            cardDtoActivateResponse.setMaskedPan("");
            cardDtoActivateResponse.setMessage("Tarjeta no existe");
        } else {
            if (cardDto.getValidationNumber() == card.getValidationNumber()) { //Si encuentra la tarjeta
                card.setActivated(true);
                card = cardRepository.save(card);

                String mp = cardDto.getPan() + "";
                String mask = "";

                mask = preMaskCardNumber(mp.length());
                maskedCard = maskCardNumber(mp, mask);

                cardDtoActivateResponse.setMaskedPan(maskedCard);
                cardDtoActivateResponse.setMessage("Éxito");
            } else {
                cardDtoActivateResponse.setMaskedPan(maskedCard);
                cardDtoActivateResponse.setMessage("Número de validacion inválido");
            }
        }
        return cardDtoActivateResponse;
    }

    public static String preMaskCardNumber(int length){
        String mask = "";
        if (length == 16) {
            mask = "######xxxxxx####";
        } else if (length == 17) {
            mask = "######xxxxxxx####";
        } else if (length == 18) {
            mask = "######xxxxxxxx####";
        } else if (length == 19) {
            mask = "######xxxxxxxxx####";
        } else {
            //poner excepcion por error en cantidad de numeros
        }
        return mask;
    }
    public static String maskCardNumber(String cardNumber, String mask) {

        // format the number
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == 'x') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }

        // return the masked number
        return maskedNumber.toString();
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

    @Override
    public ArrayList<CardDto> getAllCard() {
        return null;
    }

    @Override
    public CardDto findByPan(long pan) {
        try {
            CardDto cardDto;
            cardDto = cardDtoMapper.toDto(cardRepository.findByPan(pan));
            return cardDto;
        }catch (Exception exception){
            //throw new NotFoundException("dniNumber: " + dniNumber);
            return null;
        }
    }

    //@Override
    //public CardDto findByPan(long pan) {
     //   CardDto cardDto;
      //  cardDto = cardDtoMapper.toDto(cardRepository.findByPan(Integer.valueOf(pan)));
       // return userDto;
    //}
}
