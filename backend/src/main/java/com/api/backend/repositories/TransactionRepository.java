package com.api.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.TransactionModel;
import com.api.backend.models.UserModel;

import java.util.UUID;
import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

public interface TransactionRepository extends JpaRepository<TransactionModel, UUID>{
    Optional<TransactionModel> findByAccountFrom(Integer accountFrom);
    Optional<TransactionModel> findByAccountTo(Integer accountTo);
    Optional<List<TransactionModel>> findByUser(UserModel user);
    Optional<TransactionModel> findById(UUID id);
    Optional<List<TransactionModel>> findByDateScheduledOrderByDateScheduledAsc(LocalDateTime dateScheduled);
    Optional<List<TransactionModel>> findByDateScheduledLessThanEqual(LocalDateTime dateScheduled);
    Optional<List<TransactionModel>> findByExecutedTrue();
    Optional<List<TransactionModel>> findByExecutedFalse();
}
