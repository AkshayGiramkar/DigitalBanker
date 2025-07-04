package com.digitalbanker.repository;

import com.digitalbanker.model.Transaction;
import com.digitalbanker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
