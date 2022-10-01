package com.api.backend.services;

import com.api.backend.models.AccountModel;
import com.api.backend.repositories.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AccountService {

    final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountModel save(AccountModel account) {
        return accountRepository.save(account);
    }

    public AccountRepository getAccountRepository() {
        return this.accountRepository;
    }

    public Optional<Set<AccountModel>> findByUser(UUID user) {
        return accountRepository.findByUser(user);
    }

    public Optional<Set<AccountModel>> findByAccountNumber(Integer accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AccountService)) {
            return false;
        }
        AccountService accountService = (AccountService) o;
        return Objects.equals(accountRepository, accountService.accountRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountRepository);
    }

    @Override
    public String toString() {
        return "{" +
            " accountRepository='" + getAccountRepository() + "'" +
            "}";
    }


    public Page<AccountModel> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Optional<AccountModel> findById(UUID id) {
        return accountRepository.findById(id);
    }

    @Transactional
    public void delete(AccountModel account) {
        accountRepository.delete(account);
    }
}
