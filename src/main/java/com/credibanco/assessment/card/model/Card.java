package com.credibanco.assessment.card.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name="titular")
    private String owner;

    @Column(name="cedula")
    private long identification;  //cedula

    @Column(name="tipo")
    private char type;

    @Column(name="telefono")
    private int phone;

    @Column(name="creada")
    private boolean created;

    @Column(name="validacion")
    private int validationNumber;

    @Column(name="activada")
    private boolean activated;

}
