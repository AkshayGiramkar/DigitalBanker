package com.digitalbanker.dto;

import lombok.Data;

@Data
public class BankAccountRequest {
    private String email;
    private String accountType;
    private double initialBalance;
}
