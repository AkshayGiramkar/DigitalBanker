package com.digitalbanker.service;

import com.digitalbanker.dto.*;
import com.digitalbanker.model.*;
import com.digitalbanker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository; // ✅ Added

    public BankAccountResponse createAccount(BankAccountRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        BankAccount account = BankAccount.builder()
                .accountNumber(UUID.randomUUID().toString().substring(0, 12))
                .accountType(request.getAccountType())
                .balance(request.getInitialBalance())
                .user(user)
                .build();

        bankAccountRepository.save(account);

        return mapToResponse(account);
    }

    public List<BankAccountResponse> getAccountsByEmail(String email) {
        List<BankAccount> accounts = bankAccountRepository.findByUserEmail(email);
        return accounts.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BankAccountResponse mapToResponse(BankAccount account) {
        return BankAccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .userEmail(account.getUser().getEmail())
                .build();
    }

    public BankAccountResponse deposit(DepositRequest request) {
        BankAccount account = bankAccountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        account.setBalance(account.getBalance() + request.getAmount());
        bankAccountRepository.save(account);

        return mapToResponse(account);
    }

    public BankAccountResponse withdraw(WithdrawRequest request) {
        BankAccount account = bankAccountRepository.findByUserEmail(request.getEmail())
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - request.getAmount());
        bankAccountRepository.save(account);

        return mapToResponse(account);
    }

    public BankAccountResponse transfer(TransferRequest request) {
        BankAccount fromAccount = bankAccountRepository.findByUserEmail(request.getFromEmail())
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        BankAccount toAccount = bankAccountRepository.findByUserEmail(request.getToEmail())
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (fromAccount.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - request.getAmount());
        toAccount.setBalance(toAccount.getBalance() + request.getAmount());

        bankAccountRepository.save(fromAccount);
        bankAccountRepository.save(toAccount);

        return mapToResponse(fromAccount);
    }

    public List<Transaction> getTransactions(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return transactionRepository.findByUser(user);
    }
}
