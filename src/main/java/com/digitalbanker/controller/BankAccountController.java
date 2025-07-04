package com.digitalbanker.controller;

import com.digitalbanker.dto.BankAccountRequest;
import com.digitalbanker.dto.BankAccountResponse;
import com.digitalbanker.dto.DepositRequest;
import com.digitalbanker.dto.WithdrawRequest;
import com.digitalbanker.dto.TransferRequest;
import com.digitalbanker.model.Transaction;
import com.digitalbanker.service.BankAccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement; // ✅ Needed
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService accountService;
    
    @PostMapping
    public ResponseEntity<BankAccountResponse> createAccount(@RequestBody BankAccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<BankAccountResponse>> getUserAccounts(@PathVariable String email) {
        return ResponseEntity.ok(accountService.getAccountsByEmail(email));
    }

    @PostMapping("/deposit")
    public ResponseEntity<BankAccountResponse> deposit(@RequestBody DepositRequest request) {
        return ResponseEntity.ok(accountService.deposit(request));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccountResponse> withdraw(@RequestBody WithdrawRequest request) {
        return ResponseEntity.ok(accountService.withdraw(request));
    }

    @PostMapping("/transfer")
    public ResponseEntity<BankAccountResponse> transfer(@RequestBody TransferRequest request) {
        return ResponseEntity.ok(accountService.transfer(request));
    }

    @GetMapping("/transactions/{email}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String email) {
        return ResponseEntity.ok(accountService.getTransactions(email));
    }
}
