package com.api.backend.repositories;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.backend.models.AccountModel;
import com.api.backend.models.UserModel;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, UUID> {
    Optional<List<AccountModel>> findByUser(UserModel user);
    Optional<AccountModel> findByAccountNumber(Integer accountNumber);
}
