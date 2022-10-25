package com.credibanco.assessment.card.service.impl;

import com.credibanco.assessment.card.dto.*;
import com.credibanco.assessment.card.exceptions.BadRequestException;
import com.credibanco.assessment.card.exceptions.HandlerRequestException;
import com.credibanco.assessment.card.exceptions.NotFoundException;
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

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

        Card card = cardRepository.findByPan(transactionDto.getPan());
        String referenceNumber = transactionDto.getReferenceNumber() + "";

        if(transaction == null){
            if (referenceNumber.length() == 6){
                if (card != null){
                    if(card.isActivated()){
                        LocalDateTime now = LocalDateTime.now();
                        Timestamp timestamp = Timestamp.valueOf(now);
                        transactionDto.setTime(timestamp);
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
                        throw new BadRequestException(transactionDtoCreateResponse, "La tarjeta no está activada");
                    }
                } else {
                    transactionDtoCreateResponse.setTransactionStatus("Rechazada");
                    transactionDtoCreateResponse.setResponseCode("01");
                    transactionDtoCreateResponse.setMessage("Tarjeta No Existe");
                    transactionDtoCreateResponse.setReferenceNumber(transactionDto.getReferenceNumber());
                    throw new NotFoundException(transactionDtoCreateResponse, "Tarjeta no encontrada");
                }
            } else {
                transactionDtoCreateResponse.setMessage("Número de referencia inválido");
                transactionDtoCreateResponse.setReferenceNumber(transactionDto.getReferenceNumber());
                throw new BadRequestException(transactionDtoCreateResponse, "Número de referencia no es válido");
            }
        } else {
            transactionDtoCreateResponse.setTransactionStatus("Rechazada");
            transactionDtoCreateResponse.setResponseCode("03");
            transactionDtoCreateResponse.setMessage("Referencia de transaccion ya existe");
            transactionDtoCreateResponse.setReferenceNumber(transactionDto.getReferenceNumber());
            throw new BadRequestException(transactionDtoCreateResponse, "Numero de compra ya existe");
        }
        return transactionDtoCreateResponse;
    }

    @Override
    public TransactionDtoCancelResponse cancelTransaction(TransactionDtoCancelRequest transactionDto) {
        TransactionDtoCancelResponse transactionDtoCancelResponse = new TransactionDtoCancelResponse();
        Transaction transaction = transactionRepository.findByReferenceNumber(transactionDto.getReferenceNumber());
        long panTransaction = transactionDto.getPan();
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestampNow = Timestamp.valueOf(now);
        LocalDateTime localDateTimeBefore = transaction.getTime().toLocalDateTime();
        String minutes = String.valueOf(ChronoUnit.MINUTES.between(localDateTimeBefore, LocalDateTime.now()));
        int minutesInt = Integer.parseInt(minutes);

        int referenceNumber = transactionDto.getReferenceNumber();

        Card card = cardRepository.findByPan(panTransaction);
        if (card == null){
            transactionDtoCancelResponse = cancelResponseGeneration(referenceNumber);
            throw new BadRequestException(transactionDtoCancelResponse, "El número de tarjeta no coincide");
        }

        if(transaction != null){
            /*if(transactionDto.getPan() == card.getPan()){*/
                if(minutesInt < 5){
                    transactionRepository.delete(transaction);
                    transactionDtoCancelResponse.setResponseCode("00");
                    transactionDtoCancelResponse.setMessage("Compra anulada");
                    transactionDtoCancelResponse.setReferenceNumber(transaction.getReferenceNumber());
                } else {
                    transactionDtoCancelResponse = cancelResponseGeneration(referenceNumber);
                    throw new BadRequestException(transactionDtoCancelResponse,"Se agotó el tiempo permitido para cancelar la transacción");
                }
            /*} else {
                transactionDtoCancelResponse = cancelResponseGeneration(referenceNumber);
                throw new BadRequestException(transactionDtoCancelResponse, "El número de tarjeta no coincide");
            }*/
        } else {
            transactionDtoCancelResponse = cancelResponseGeneration(referenceNumber);
            throw new NotFoundException(transactionDtoCancelResponse,"No existe la transacción");
        }
        return transactionDtoCancelResponse;
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
    public TransactionDtoCancelResponse cancelResponseGeneration( int referenceNumber){
        TransactionDtoCancelResponse transactionDtoCancelResponse = new TransactionDtoCancelResponse();
        transactionDtoCancelResponse.setResponseCode("00");
        transactionDtoCancelResponse.setMessage("No se puede anular la transaccion");
        transactionDtoCancelResponse.setReferenceNumber(referenceNumber);
        return transactionDtoCancelResponse;
    }
}
