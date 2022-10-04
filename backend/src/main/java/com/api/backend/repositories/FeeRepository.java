package com.api.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.backend.models.FeeModel;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<FeeModel, UUID> {
    Optional<FeeModel> findByIntervalMinLessThanAndIntervalMaxGreaterThanEqual(Integer interval_min, Integer interval_max);
    Optional<List<FeeModel>> findByIntervalMinLessThan(Integer interval);  
    Optional<List<FeeModel>> findByIntervalMaxGreaterThanEqual(Integer interval);    
    Optional<FeeModel> findByValueMinLessThanAndValueMaxGreaterThanEqual(Integer value_min, Integer value_max);
    Optional<List<FeeModel>> findByValueMinLessThan(Float value); 
    Optional<List<FeeModel>> findByValueMaxGreaterThanEqual(Float value); 
}
