package com.digitalbanker.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private String fromEmail;
    private String toEmail;
    private double amount;
}
