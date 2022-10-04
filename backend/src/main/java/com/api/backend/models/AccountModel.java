package com.api.backend.models;

import javax.persistence.*;
import java.io.Serializable;
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
    private Float value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private UserModel user;

    @ManyToMany
    @JoinTable(name = "TB_ACCOUNTS_TRANSACTIONS",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private List<TransactionModel> transactions;


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

    public Float getValue() {
        return value;
    }
    public void setValue(Float value) {
        this.value = value;
    }
    
    public void setUser(UserModel user) {
        this.user = user;
    }
}
