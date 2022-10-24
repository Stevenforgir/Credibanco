package com.credibanco.assessment.card.mapper.impl;

import com.credibanco.assessment.card.dto.TransactionDto;
import com.credibanco.assessment.card.mapper.TransactionDaoMapper;
import com.credibanco.assessment.card.model.Transaction;

public class TransactionDaoMapperImpl implements TransactionDaoMapper{
    @Override
    public Transaction toDao(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setPan(transactionDto.getPan());
        transaction.setPurchaseAddress(transactionDto.getPurchaseAddress());
        transaction.setReferenceNumber(transactionDto.getReferenceNumber());
        transaction.setTotalPurchase(transactionDto.getTotalPurchase());
        return transaction;
    }
}
