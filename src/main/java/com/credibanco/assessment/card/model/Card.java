package com.credibanco.assessment.card.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name="cards", uniqueConstraints = {@UniqueConstraint(columnNames = {"pan"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //data identificator

    @Column(name="pan")
    private long pan;

    @Column(name="owner")
    private String owner;

    @Column(name="identification")
    private long identification;  //cedula

    @Column(name="type")
    private boolean type; //0 Debit card, 1 credit card

    @Column(name="phone")
    private int phone;

}
