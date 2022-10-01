package com.api.backend.repositories;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.backend.models.AccountModel;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, UUID> {
    Optional<Set<AccountModel>> findByUser(UUID user);
    Optional<Set<AccountModel>> findByAccountNumber(Integer accountNumber);
}
