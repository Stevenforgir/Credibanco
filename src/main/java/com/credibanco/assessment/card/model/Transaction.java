package com.credibanco.assessment.card.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="transactions", uniqueConstraints = {@UniqueConstraint(columnNames = {"referencia"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //data identificator

    @Column(name="pan")
    private long pan;

    @Column(name="referencia")
    private int referenceNumber;

    @Column(name="compra")
    private int totalPurchase;

    @Column(name="direccion")
    private String purchaseAddress;

    @Column(name="fecha")
    @CreationTimestamp
    private Timestamp time;
}
