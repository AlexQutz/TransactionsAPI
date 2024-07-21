package com.example.transactionsapi.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

/**
 * Transaction Model.
 */
@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sourceAccountId;
    private Long targetAccountId;
    private Double amount;
    private String currency;

}