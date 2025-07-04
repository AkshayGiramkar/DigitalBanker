package com.digitalbanker.repository;

import com.digitalbanker.model.BankAccount;
import com.digitalbanker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByUser(User user);
    List<BankAccount> findByUserEmail(String email);
    Optional<BankAccount> findByAccountNumber(String accountNumber);
}
