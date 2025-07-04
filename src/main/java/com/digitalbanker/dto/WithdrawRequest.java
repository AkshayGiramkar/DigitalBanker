package com.digitalbanker.dto;

import lombok.Data;

@Data
public class WithdrawRequest {
    private String email;
    private double amount;
}
