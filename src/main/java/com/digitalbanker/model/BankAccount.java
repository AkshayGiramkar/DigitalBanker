package com.digitalbanker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber; // Avoid setting default value here unless needed for logic

    private String accountType;

    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
