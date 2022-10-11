package com.api.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.backend.models.FeeModel;
import com.api.backend.repositories.FeeRepository;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class FeeService {
    @Autowired
    final FeeRepository feeRepository;

    public FeeService(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    public FeeRepository getFeeRepository() {
        return this.feeRepository;
    }

    public Optional<FeeModel> findByIntervalMinLessThanAndIntervalMaxGreaterThanEqual(Integer interval) {
        return feeRepository.findByIntervalMinLessThanAndIntervalMaxGreaterThanEqual(interval, interval);
    }

    public Optional<List<FeeModel>> findByIntervalMinLessThan(Integer interval) {
        return feeRepository.findByIntervalMinLessThan(interval);
    }

    public Optional<List<FeeModel>> findByIntervalMaxGreaterThanEqual(Integer interval) {
        return feeRepository.findByIntervalMaxGreaterThanEqual(interval);
    }

    public Optional<List<FeeModel>> findByValueMinLessThanAndValueMaxGreaterThanEqual(BigDecimal value) {
        return feeRepository.findByValueMinLessThanAndValueMaxGreaterThanEqual(value, value);
    }

    public Optional<List<FeeModel>> findByValueMinLessThan(Double value) {
        return feeRepository.findByValueMinLessThan(value);
    }

    public Optional<List<FeeModel>> findByValueMaxGreaterThanEqual(Double value) {
        return feeRepository.findByValueMaxGreaterThanEqual(value);
    }

    public List<FeeModel> findAll() {
        return feeRepository.findAll();
    }

    public Optional<FeeModel> findById(UUID id) {
        return feeRepository.findById(id);
    }

    public Optional<FeeModel> findByType(String type) {
        return feeRepository.findByType(type);
    }
}
