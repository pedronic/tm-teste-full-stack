package com.api.backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import java.time.LocalDateTime;


@Entity
@Table(name = "TB_TRANSACTION")
public class TransactionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID transactionId;
    
    @Column(name="account_from",nullable = false)
    private Integer accountFrom;
    @Column(name="account_to",nullable = false)
    private Integer accountTo;
    @Column(name="value",nullable = false)
    private Float value;
    @Column(name="fee_value",nullable = false)
    private Float fee_value;
    @Column(name="date_created",nullable = false)
    private LocalDateTime dateCreated;
    @Column(name="date_scheduled",nullable = false)
    private LocalDateTime dateScheduled;
    @Column(name="executed",nullable = false)
    private boolean executed;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fee_id")
    private FeeModel fee;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private UserModel user;
    
    
    public UUID getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }
    public Integer getAccountFrom() {
        return accountFrom;
    }
    public void setAccountFrom(Integer accountFrom) {
        this.accountFrom = accountFrom;
    }
    public Integer getAccountTo() {
        return accountTo;
    }
    public void setAccountTo(Integer accountTo) {
        this.accountTo = accountTo;
    }
    public Float getValue() {
        return value;
    }
    public void setValue(Float value) {
        this.value = value;
    }
    public Float getFee_value() {
        return fee_value;
    }
    public void setFee_value(Float fee_value) {
        this.fee_value = fee_value;
    }
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    public LocalDateTime getDateScheduled() {
        return dateScheduled;
    }
    public void setDateScheduled(LocalDateTime dateScheduled) {
        this.dateScheduled = dateScheduled;
    }
    public boolean isExecuted() {
        return executed;
    }
    public void setExecuted(boolean executed) {
        this.executed = executed;
    }
    public FeeModel getFee() {
        return fee;
    }
    public void setFee(FeeModel fee) {
        this.fee = fee;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
    
    
}
