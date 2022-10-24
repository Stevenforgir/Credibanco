package com.credibanco.assessment.card.repository;

import com.credibanco.assessment.card.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findByReferenceNumber(int referenceNumber);
}
