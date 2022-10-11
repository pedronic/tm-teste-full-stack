package com.api.backend.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import org.checkerframework.checker.index.qual.Positive;
import org.springframework.format.annotation.DateTimeFormat;

public class TransactionDto {

    @NotBlank
    private String username;
    @NotBlank
    private Integer accountFrom;
    @NotBlank
    private Integer accountTo;
    @NotBlank
    @Positive
    private BigDecimal value;
    @NotBlank
    @DateTimeFormat
    private LocalDateTime dateScheduled;
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDateTime getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(LocalDateTime dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

}
