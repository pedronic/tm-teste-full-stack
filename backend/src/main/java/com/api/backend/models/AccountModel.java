package com.api.backend.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "TB_ACCOUNT")
public class AccountModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountId;

    @Column(name="account_number",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer accountNumber;
    @Column(name="value",nullable = false)
    private BigDecimal value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @JsonBackReference
    private UserModel user;

    @ManyToMany
    @JoinTable(name = "TB_ACCOUNTS_TRANSACTIONS",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private List<TransactionModel> transactions;

    public AccountModel(){
        this.accountId = UUID.randomUUID();
        this.value = new BigDecimal(0);
    }

    public AccountModel(Integer accountNumber){
        this.accountId = UUID.randomUUID();
        this.accountNumber = accountNumber;
    }
    public AccountModel(Integer accountNumber, BigDecimal value){
        this.accountId = UUID.randomUUID();
        this.accountNumber = accountNumber;
        this.value = value;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
    

    public UserModel getUser() {
        return user;
    }
    public void setUserId(UserModel userId) {
        this.user = userId;
    }

    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    public void setUser(UserModel user) {
        this.user = user;
    }
}
