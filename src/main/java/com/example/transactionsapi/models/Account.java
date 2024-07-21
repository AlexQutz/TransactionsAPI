package com.example.transactionsapi.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

/**
 * The Account model.
 */
@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    private String currency;

    private Instant createdAt;

}