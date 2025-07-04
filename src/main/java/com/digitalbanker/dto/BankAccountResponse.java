package com.digitalbanker.dto;

import com.digitalbanker.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccountResponse {
    private String accountNumber;
    private String accountType;
    private double balance;
    private String userEmail; // 👈 Make sure this matches the method you're calling
}
