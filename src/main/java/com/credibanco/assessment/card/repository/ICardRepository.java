package com.credibanco.assessment.card.repository;

import com.credibanco.assessment.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<Card, Long> {
    Card findByPan(long pan);
}
