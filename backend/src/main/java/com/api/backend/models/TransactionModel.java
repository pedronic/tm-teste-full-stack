package com.api.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

//// TO DO


@Entity
@Table(name = "TB_TRANSACTION")
public class TransactionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountId;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="account_n",nullable = false)
    private Integer accountNumber;
    @Column(name="value",nullable = false)
    private Float value;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private UserModel user;

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
    
}
