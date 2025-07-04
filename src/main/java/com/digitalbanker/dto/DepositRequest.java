package com.digitalbanker.dto;

import lombok.*;

@Data
public class DepositRequest {
    private String accountNumber;
    private double amount;
}
