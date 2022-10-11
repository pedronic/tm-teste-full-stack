package com.api.backend.services;

import com.api.backend.models.AccountModel;
import com.api.backend.models.UserModel;
import com.api.backend.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
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

    public Optional<List<AccountModel>> findByUser(UserModel user) {
        return accountRepository.findByUser(user);
    }

    public Optional<AccountModel> findByAccountNumber(Integer accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Integer max() {
        List<Integer> _max = new ArrayList<>();
        _max.add(0);
        accountRepository.findAll().stream().forEach(account -> {
            if( account.getAccountNumber() > _max.get(_max.size()-1) ) {
                _max.add(account.getAccountNumber());
            }
        });
        return _max.get(_max.size()-1);
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
        var acc = accountRepository.findAll(pageable);
        return acc;
    }

    public Optional<AccountModel> findById(UUID id) {
        return accountRepository.findById(id);
    }

    @Transactional
    public void delete(AccountModel account) {
        accountRepository.delete(account);
    }
}
