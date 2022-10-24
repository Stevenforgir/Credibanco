package com.credibanco.assessment.card.service.impl;

import com.credibanco.assessment.card.dto.CardDto;
import com.credibanco.assessment.card.dto.TransactionDto;
import com.credibanco.assessment.card.dto.TransactionDtoCreateResponse;
import com.credibanco.assessment.card.mapper.CardDaoMapper;
import com.credibanco.assessment.card.mapper.CardDtoMapper;
import com.credibanco.assessment.card.mapper.TransactionDaoMapper;
import com.credibanco.assessment.card.mapper.TransactionDtoMapper;
import com.credibanco.assessment.card.mapper.impl.CardDaoMapperImpl;
import com.credibanco.assessment.card.mapper.impl.CardDtoMapperImpl;
import com.credibanco.assessment.card.mapper.impl.TransactionDaoMapperImpl;
import com.credibanco.assessment.card.mapper.impl.TransactionDtoMapperImpl;
import com.credibanco.assessment.card.model.Card;
import com.credibanco.assessment.card.model.Transaction;
import com.credibanco.assessment.card.repository.ICardRepository;
import com.credibanco.assessment.card.repository.ITransactionRepository;
import com.credibanco.assessment.card.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    TransactionDaoMapper transactionDaoMapper = new TransactionDaoMapperImpl();
    TransactionDtoMapper transactionDtoMapper = new TransactionDtoMapperImpl();
    CardDaoMapper cardDaoMapper = new CardDaoMapperImpl();
    CardDtoMapper cardDtoMapper = new CardDtoMapperImpl();

    @Autowired
    ITransactionRepository transactionRepository;
    @Autowired
    ICardRepository cardRepository;

    @Override
    public TransactionDtoCreateResponse createTransaction(TransactionDto transactionDto) {
        TransactionDtoCreateResponse transactionDtoCreateResponse = new TransactionDtoCreateResponse();
        Transaction transaction = transactionRepository.findByReferenceNumber(transactionDto.getReferenceNumber());
        long panTransaction = transactionDto.getPan();

        Card card = cardRepository.findByPan(panTransaction);
        String referenceNumber = transactionDto.getReferenceNumber() + "";

        if(transaction == null){
            if (referenceNumber.length() == 6){
                if (card != null){
                    if(card.isActivated()){
                        Transaction saveTransaction =  transactionRepository.save(transactionDaoMapper.toDao(transactionDto));
                        transactionDto = transactionDtoMapper.toDto(saveTransaction);

                        transactionDtoCreateResponse.setTransactionStatus("Aprobada");
                        transactionDtoCreateResponse.setResponseCode("00");
                        transactionDtoCreateResponse.setMessage("Compra Exitosa");
                        transactionDtoCreateResponse.setReferenceNumber(transactionDto.getReferenceNumber());
                    } else {
                        transactionDtoCreateResponse.setTransactionStatus("Rechazada");
                        transactionDtoCreateResponse.setResponseCode("02");
                        transactionDtoCreateResponse.setMessage("Tarjeta No Enrolada");
                        transactionDtoCreateResponse.setReferenceNumber(transactionDto.getReferenceNumber());
                    }
                } else {
                    transactionDtoCreateResponse.setTransactionStatus("Rechazada");
                    transactionDtoCreateResponse.setResponseCode("01");
                    transactionDtoCreateResponse.setMessage("Tarjeta No Existe");
                    transactionDtoCreateResponse.setReferenceNumber(transactionDto.getReferenceNumber());
                }
            } else {
                transactionDtoCreateResponse.setMessage("Número de referencia inválido");
                transactionDtoCreateResponse.setReferenceNumber(transactionDto.getReferenceNumber());
            }
        } else {
            transactionDtoCreateResponse.setTransactionStatus("Rechazada");
            transactionDtoCreateResponse.setResponseCode("03");
            transactionDtoCreateResponse.setMessage("Referencia de transaccion ya existe");
            transactionDtoCreateResponse.setReferenceNumber(transactionDto.getReferenceNumber());
        }
        return transactionDtoCreateResponse;
    }

    @Override
    public TransactionDto findByReferenceNumber(int referenceNumber) {
        try {
            TransactionDto transactionDto;
            transactionDto = transactionDtoMapper.toDto(transactionRepository.findByReferenceNumber(referenceNumber));
            return transactionDto;
        }catch (Exception exception){
            //throw new NotFoundException("dniNumber: " + dniNumber);
            return null;
        }
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
}
