package com.api.backend.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.time.LocalDateTime;


@Entity
@Table(name = "TB_TRANSACTION")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "transactionId" )
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
    private BigDecimal value;
    @Column(name="fee_value",nullable = false)
    private BigDecimal fee_value;
    @Column(name="date_created",nullable = false)
    private LocalDateTime dateCreated;
    @Column(name="date_scheduled",nullable = false)
    private LocalDateTime dateScheduled;
    @Column(name="executed",nullable = false)
    private boolean executed;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fee_id")
    @JsonBackReference
    private FeeModel fee;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @JsonBackReference
    private UserModel user;
    
    public TransactionModel(){
        this.transactionId = UUID.randomUUID();
        this.dateCreated = LocalDateTime.now();
        this.executed = false;
    }
    
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
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public BigDecimal getFee_value() {
        return fee_value;
    }
    public void setFee_value(BigDecimal fee_value) {
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

    @Override
    public String toString() {
        return "{\n"+
        "\t \"transactionId\": \""+ getTransactionId().toString()+"\",\n"+
        "\t \"accountFrom\": "+ getAccountFrom().toString()+",\n"+
        "\t \"accountTo\": "+ getAccountTo().toString()+",\n"+
        "\t \"value\": "+ getValue().setScale(2, RoundingMode.UP)+",\n"+
        "\t \"fee_value\": "+ getFee_value().setScale(2, RoundingMode.UP)+",\n"+
        "\t \"dateCreated\": \""+ getDateCreated()+"\",\n"+
        "\t \"dateScheduled\": \""+ getDateScheduled()+"\",\n"+
        "\t \"executed\": "+ isExecuted() +",\n"+
        "\t \"fee\": \""+ getFee().getType()+"\"\n"+
        "}";
    }
    
}
