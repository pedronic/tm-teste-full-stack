package com.api.backend.services;

import org.springframework.stereotype.Service;

import com.api.backend.models.FeeModel;
import com.api.backend.repositories.FeeRepository;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
public class FeeService {
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

    public Optional<FeeModel> findByValueMinLessThanAndValueMaxGreaterThanEqual(Integer value) {
        return feeRepository.findByValueMinLessThanAndValueMaxGreaterThanEqual(value, value);
    }

    public Optional<List<FeeModel>> findByValueMinLessThan(Float value) {
        return feeRepository.findByValueMinLessThan(value);
    }

    public Optional<List<FeeModel>> findByValueMaxGreaterThanEqual(Float value) {
        return feeRepository.findByValueMaxGreaterThanEqual(value);
    }

    public List<FeeModel> findAll() {
        return feeRepository.findAll();
    }

    public Optional<FeeModel> findById(UUID id) {
        return feeRepository.findById(id);
    }
}
