package com.credibanco.assessment.card.mapper;

import com.credibanco.assessment.card.dto.TransactionDto;
import com.credibanco.assessment.card.model.Transaction;

public interface TransactionDtoMapper {
    TransactionDto toDto(Transaction transaction);
}
