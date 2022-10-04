package com.api.backend.services;

import org.springframework.stereotype.Service;

import com.api.backend.models.TransactionModel;
import com.api.backend.repositories.TransactionRepository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public TransactionRepository getTransactionRepository() {
        return this.transactionRepository;
    }

    public Optional<TransactionModel> findByAccountFrom(Integer accountFrom) {
        return transactionRepository.findByAccountFrom(accountFrom);
    }
    
    public Optional<TransactionModel> findByAccountTo(Integer accountTo) {
        return transactionRepository.findByAccountTo(accountTo);
    }
    
    public Optional<List<TransactionModel>> findByUser(UUID user) {
        return transactionRepository.findByUser(user);
    }
    
    public Optional<List<TransactionModel>> findByDateScheduled(LocalDateTime dateScheduled) {
        return transactionRepository.findByDateScheduledOrderByDateScheduledAsc(dateScheduled);
    }
    
    public Optional<List<TransactionModel>> findByDateScheduledLessThanEqual(LocalDateTime dateScheduled) {
        return transactionRepository.findByDateScheduledLessThanEqual(dateScheduled);
    }
    
    public boolean isExecuted(UUID transactionId) {
        return transactionRepository.findById(transactionId).get().isExecuted();        
    }
    
    public Optional<List<TransactionModel>> findByExecutedTrue() {
        return transactionRepository.findByExecutedTrue();
    }
    
    public Optional<List<TransactionModel>> findByExecutedFalse() {
        return transactionRepository.findByExecutedFalse();
    }
    
}
