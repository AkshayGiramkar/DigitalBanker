package com.digitalbanker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // DEPOSIT, WITHDRAW, TRANSFER
    private Double amount;
    private LocalDateTime timestamp;

    private String fromAccount;
    private String toAccount; // Optional: may be null for deposit/withdraw

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
